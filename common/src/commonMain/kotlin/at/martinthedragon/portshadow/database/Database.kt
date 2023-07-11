package at.martinthedragon.portshadow.database

import org.jetbrains.exposed.sql.Database
import java.nio.file.Path

object ShadowBase {
    fun connect(connection: DatabaseConnection, settings: DatabaseSettings): Database {
        val settingsString = settings.additionalSettings
            .map(DatabaseSettings.ExplicitSetting::toSettingString)
            .filter(String::isNotBlank)
            .joinToString(";")
        return Database.connect("jdbc:h2:${connection.scheme};$settingsString", user = settings.username, password = settings.filePassword + ' ' + settings.password)
    }
}

sealed class DatabaseConnection(val scheme: String) {
    class DebugConnection : DatabaseConnection("mem:test")
    class FileConnection(file: Path) : DatabaseConnection("file:$file")
    class TcpConnection(url: String) : DatabaseConnection("tcp://$url")
    class SslConnection(url: String) : DatabaseConnection("ssl://$url")
}

data class DatabaseSettings(
    val username: String = "",
    val password: String = "", // API Note: May not contain spaces!
    val filePassword: String = "", // API Note: May not contain spaces!
    val additionalSettings: List<ExplicitSetting> = emptyList(),
) {
    fun interface ExplicitSetting {
        fun toSettingString(): String
    }

    // TODO add ability to change encryption
    enum class EncryptionScheme : ExplicitSetting {
        NONE, AES, XTEA, FOG;

        override fun toSettingString() = if (this == NONE) "" else "CIPHER=$name"
    }

    enum class LockMode : ExplicitSetting {
        NO, FILE, SOCKET, FS;

        override fun toSettingString() = if (this == FILE) "" else "FILE_LOCK=$name"
    }

    object OnlyIfExists : ExplicitSetting {
        override fun toSettingString() = "IFEXISTS=TRUE"
    }

    class CloseDelay(val seconds: Int) : ExplicitSetting {
        override fun toSettingString() = "DB_CLOSE_DELAY=$seconds"

        companion object {
            val PERSISTENT = CloseDelay(-1)
        }
    }

    object NoCloseOnExit : ExplicitSetting {
        override fun toSettingString() = "DB_CLOSE_ON_EXIT=FALSE"
    }

    object IgnoreUnknownSettings : ExplicitSetting {
        override fun toSettingString() = "IGNORE_UNKNOWN_SETTINGS=TRUE"
    }
}
