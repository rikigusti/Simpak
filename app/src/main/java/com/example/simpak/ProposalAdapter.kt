import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpak.R

class ProposalAdapter(private var proposalList: List<Proposal>) :
    RecyclerView.Adapter<ProposalAdapter.ProposalViewHolder>(), Filterable {

    private var filteredList = proposalList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProposalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_proposal, parent, false)
        return ProposalViewHolder(view)
    }

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: ProposalViewHolder, position: Int) {
        val proposal = filteredList[position]
        holder.bind(proposal)
    }

    fun updateData(newList: List<Proposal>) {
        proposalList = newList
        filteredList = newList.toMutableList()
        notifyDataSetChanged()
    }

    inner class ProposalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(proposal: Proposal) {
            val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
            val tvAuthor = itemView.findViewById<TextView>(R.id.tvAuthor)
            val tvTime = itemView.findViewById<TextView>(R.id.tvTime)
            val ivStatus = itemView.findViewById<ImageView>(R.id.ivStatus)

            tvTitle.text = proposal.title
            tvAuthor.text = proposal.applicant
            tvTime.text = proposal.timeRemaining

            // Menentukan ikon status berdasarkan nilai status
            val statusIcon = when (proposal.status.lowercase()) {
                "disetujui" -> R.drawable.ic_status_approved
                "ditolak" -> R.drawable.ic_status_rejected
                else -> R.drawable.ic_status_pending // default: menunggu
            }

            ivStatus.setImageResource(statusIcon)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val keyword = constraint?.toString()?.lowercase()?.trim() ?: ""
                val results = if (keyword.isEmpty()) {
                    proposalList
                } else {
                    proposalList.filter {
                        it.title.lowercase().contains(keyword) ||
                                it.applicant.lowercase().contains(keyword)
                    }
                }

                return FilterResults().apply { values = results }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = (results?.values as? List<Proposal>)?.toMutableList() ?: mutableListOf()
                notifyDataSetChanged()
            }
        }
    }
}
