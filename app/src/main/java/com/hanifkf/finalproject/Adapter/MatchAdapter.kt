package com.hanifkf.finalproject.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hanifkf.finalproject.Model.Match
import com.hanifkf.finalproject.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_match.view.*
import java.text.SimpleDateFormat
import java.util.*

class MatchAdapter(
    private val context: Context,
    private val items: List<Match>,
    private val listener: (Match) -> Unit
) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_match, parent, false)

        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(items: Match, listener: (Match) -> Unit) {

            itemView.home_team.text = items.homeTeam
            itemView.away_team.text = items.awayTeam
            itemView.home_score.text = items.homeScore
            itemView.away_score.text = items.awayScore
            itemView.date_match.text = items.eventDate?.formatDateToMatch()
            itemView.time_match.text = items.eventTime?.formatTimeToMatch()

            containerView.setOnClickListener {
                listener(items)
            }


        }

        var tz: TimeZone = TimeZone.getTimeZone("WIB")

        fun String.formatTimeToMatch(
            inputFormat: String = "HH:mm:ss",
            outputFormat: String? = "HH:mm"
        ): String {

            val timeFormat = SimpleDateFormat(inputFormat, Locale.ENGLISH)
            timeFormat.timeZone = tz
            val time = timeFormat.parse(this)

            val returnFormat = SimpleDateFormat(outputFormat, Locale.ENGLISH)
            return returnFormat.format(time)
        }

        fun Date.formatDateToMatch(): String? {
            return SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(this)
        }

    }

}