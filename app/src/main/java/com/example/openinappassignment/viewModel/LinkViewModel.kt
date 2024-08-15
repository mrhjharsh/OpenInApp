import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.openinappassignment.model.ApiResponse
import com.example.openinappassignment.repository.LinkRepository

class LinkViewModel() : ViewModel() {

    val repository = LinkRepository()

    fun getLiveData(): MutableLiveData<ApiResponse> {
        return repository.getLiveData();
    }
}