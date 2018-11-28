package com.hanifkf.finalproject.Detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.hanifkf.finalproject.ApiRepository.ApiRepository
import com.hanifkf.finalproject.Model.Player
import com.hanifkf.finalproject.Presenter.PlayerPresenter
import com.hanifkf.finalproject.R
import com.hanifkf.finalproject.View.PlayerView
import kotlinx.android.synthetic.main.activity_detail_player.*
import org.jetbrains.anko.ctx

class DetailPlayerActivity : AppCompatActivity(),PlayerView {

    private lateinit var presenter:PlayerPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        val request = ApiRepository()
        val gson = Gson()
        intent  = intent
        val idPlayer = intent.getStringExtra("idPlayer")
        val strPlayer = intent.getStringExtra("strPlayer")

        presenter = PlayerPresenter(this, request, gson)
        supportActionBar?.title = strPlayer
        presenter.getDetailPlayer(idPlayer)

    }

    override fun showLoading() {
        progressPlayer.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressPlayer.visibility = View.GONE
    }

    override fun showPlayer(data: List<Player>?) {
        if(data!=null){
            Glide.with(ctx).load(data[0].strFanart1).into(baner)
            Glide.with(ctx).load(data[0].strCutout).into(pht_player)
            player_position.text = data[0].strPosition
            player_weight.text = data[0].strWeight+" Kg"
            player_height.text = data[0].strHeight+" m"
            player_desc.text = data[0].strDescriptionEN
        }
    }

}
