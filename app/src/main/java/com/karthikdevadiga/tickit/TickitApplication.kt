import android.app.Application
import com.karthikdevadiga.tickit.data.TaskDao
import com.karthikdevadiga.tickit.data.TaskDao_Impl
import com.karthikdevadiga.tickit.data.TickitDatabase

class TickitApplication:Application {

    override fun onCreate(){
        super.onCreate()
        database =TickitDatabase.createDatabase()
        taskdao= database.getTaskDao()
    }

    companion object{
        lateinit var database:TickitDatabase
        lateinit var taskdao:TaskDao
    }
}