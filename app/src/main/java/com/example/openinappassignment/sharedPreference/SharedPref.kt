
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.transition.Visibility.Mode
import com.example.openinappassignment.R

class SharedPref(private val context: Context) {
    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), 0)
    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    private val API_KEY = "apiKey"

    fun setAPIKey(){
        editor.putString(API_KEY, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI")
        editor.commit()
    }

    fun getAPIKey(): String?{
        return sharedPreferences.getString(API_KEY, "")
    }
}