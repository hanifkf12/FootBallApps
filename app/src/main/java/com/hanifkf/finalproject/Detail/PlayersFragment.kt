package com.hanifkf.finalproject.Detail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.hanifkf.finalproject.Adapter.PlayerAdapter
import com.hanifkf.finalproject.ApiRepository.ApiRepository
import com.hanifkf.finalproject.Model.Player
import com.hanifkf.finalproject.Presenter.PlayerPresenter

import com.hanifkf.finalproject.R
import com.hanifkf.finalproject.View.PlayerView
import kotlinx.android.synthetic.main.fragment_players.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PlayersFragment : Fragment(),PlayerView {
    private lateinit var presenter: PlayerPresenter
    private var player: MutableList<Player> = mutableListOf()
    private lateinit var adapter: PlayerAdapter

    companion object {
        fun newInstance(teamId: String) : PlayersFragment{
            val bundle = Bundle()
            bundle.putString("teamId", teamId)
            val fragmntku = PlayersFragment()
            fragmntku.arguments = bundle
            return fragmntku
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val apiRepository = ApiRepository()
        val gson = Gson()
        val teamId = arguments?.getString("teamId").toString()
        presenter = PlayerPresenter(this,apiRepository,gson)
        adapter = PlayerAdapter(ctx,player){
            startActivity<DetailPlayerActivity>("idPlayer" to it.idPlayer , "strPlayer" to it.strPlayer)
        }
        recycle_player.layoutManager = LinearLayoutManager(ctx)
        recycle_player.adapter = adapter
        presenter.getPlayers(teamId)

    }

    override fun showLoading() {
        progressBarplayer.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarplayer.visibility = View.GONE
    }

    override fun showPlayer(data: List<Player>?) {
        if(data!=null){
            player.clear()
            player.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }


}
