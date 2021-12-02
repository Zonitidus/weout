package edu.co.icesi.weout.recycler.expanded

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.weout.R

class ExpandedView(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var titulo: TextView = itemView.findViewById(R.id.tituloTVE)

    var imageBtn1: ImageButton = itemView.findViewById(R.id.imageBtn1)
    var lugar1: TextView = itemView.findViewById(R.id.lugar1TV)
    var fecha1: TextView = itemView.findViewById(R.id.fecha1TV)

    var imageBtn2: ImageButton = itemView.findViewById(R.id.imageBtn2)
    var lugar2: TextView = itemView.findViewById(R.id.lugar2TV)
    var fecha2: TextView = itemView.findViewById(R.id.fecha2TV)

    var imageBtn3: ImageButton = itemView.findViewById(R.id.imageBtn3)
    var lugar3: TextView = itemView.findViewById(R.id.lugar3TV)
    var fecha3: TextView = itemView.findViewById(R.id.fecha3TV)

    var imageBtn4: ImageButton = itemView.findViewById(R.id.imageBtn4)
    var lugar4: TextView = itemView.findViewById(R.id.lugar4TV)
    var fecha4: TextView = itemView.findViewById(R.id.fecha4TV)
}
