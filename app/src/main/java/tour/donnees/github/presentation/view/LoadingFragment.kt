package tour.donnees.github.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import tour.donnees.github.R
import tour.donnees.github.databinding.FragmentLoadingBinding
import tour.donnees.github.presentation.viewmodel.MainViewModel

class LoadingFragment : Fragment() {

    private lateinit var binding: FragmentLoadingBinding
    private val viewModel by activityViewModel<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.setOnClickListener {
            //Navigation.findNavController(it).navigate(R.id.navigateToListFragmentAction)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoadingBinding.inflate(inflater)
        return binding.root
    }
}