package com.hanifkf.finalproject.Detail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.hanifkf.finalproject.ApiRepository.ApiRepository
import com.hanifkf.finalproject.Model.Team
import com.hanifkf.finalproject.Presenter.TeamPresenter

import com.hanifkf.finalproject.R
import com.hanifkf.finalproject.View.TeamView
import kotlinx.android.synthetic.main.fragment_detail_overview.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailOverviewFragment : Fragment(), TeamView {
    companion object {
        fun newInstance(teamId: String) : DetailOverviewFragment{
            val bundle = Bundle()
            bundle.putString("teamId", teamId)
            val fragmntku = DetailOverviewFragment()
            fragmntku.arguments = bundle
            return fragmntku
        }
    }


    private lateinit var presenter: TeamPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_overview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val teamId = arguments?.getString("teamId").toString()
        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this,apiRepository,gson)

            presenter.getDetailTeams(teamId)


    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showTeam(data: List<Team>?) {
        if(data!=null){
            txt_description.text= data[0].teamDescription
        }
    }
}
