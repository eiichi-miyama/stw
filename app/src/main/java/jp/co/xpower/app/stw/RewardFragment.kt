package jp.co.xpower.app.stw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.co.xpower.app.stw.databinding.FragmentRallyListDialogItemBinding
import jp.co.xpower.app.stw.databinding.FragmentRallyPublicBinding
import jp.co.xpower.app.stw.databinding.FragmentRewardBinding
import jp.co.xpower.app.stw.databinding.FragmentRewardItemBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RewardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RewardFragment : Fragment(), RewardClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentRewardBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRewardBinding.inflate(inflater, container, false)

        binding.buttonBack.setOnClickListener {
            binding.list.visibility = View.VISIBLE
            binding.detail.visibility = View.INVISIBLE
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        populateReward()
        binding.recyclerView.layoutManager = GridLayoutManager(context, 1)
        binding.recyclerView.adapter = ItemAdapter(rallyList, this)
    }

    private fun populateReward() {
        for (i in 1..10) {
            var rally = Rally(
                R.drawable.rally,
                "学園祭 - %d".format(i),
                "報酬１２３４５\n報酬１２３４５\n報酬１２３４５\n報酬１２３４５\n報酬１２３４５\n"
            )
            rallyList.add(rally)
        }
    }

    private inner class ViewHolder internal constructor(private val binding: FragmentRewardItemBinding, private val clickListener: RewardClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindRally(rally: Rally){
            binding.cover.setImageResource(rally.cover)
            binding.title.text = rally.title
            binding.description.text = rally.description
            binding.cardView.setOnClickListener{
                clickListener.onClick(rally)
            }
        }
    }
    private inner class ItemAdapter internal constructor(
        private val rallys: List<Rally>,
        private val clickListener: RewardClickListener
    ) :
        RecyclerView.Adapter<RewardFragment.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            return ViewHolder(
                FragmentRewardItemBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                ),
                clickListener
            )
        }

        override fun onBindViewHolder(holder: RewardFragment.ViewHolder, position: Int) {
            holder.bindRally(rallys[position])
        }

        override fun getItemCount(): Int {
            return rallys.size
        }
    }

    override fun onClick(rally: Rally) {
        binding.list.visibility = View.INVISIBLE
        binding.detail.visibility = View.VISIBLE
    }

        companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RewardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RewardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}