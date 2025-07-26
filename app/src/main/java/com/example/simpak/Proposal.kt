data class Proposal(
    val title: String,
    val author: String,
    val timeRemaining: String,
    val status: String
) {
    val applicant: String
        get() = author
}
