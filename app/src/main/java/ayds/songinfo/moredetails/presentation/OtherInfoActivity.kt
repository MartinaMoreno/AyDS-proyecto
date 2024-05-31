package ayds.songinfo.moredetails.presentation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import ayds.songinfo.R
import ayds.songinfo.moredetails.injector.OtherInfoInjector
import com.squareup.picasso.Picasso

class OtherInfoActivity: Activity() {
    private lateinit var cardContentTextView: TextView
    private lateinit var openUrlButton: Button
    private lateinit var sourceImageView: ImageView

    private lateinit var presenter: OtherInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_info)

        initViewProperties()
        initPresenter()

        observerPresenter()
        getArtistCardAsync()
    }

    private fun initViewProperties() {
        cardContentTextView = findViewById(R.id.cardContent1TextView)
        openUrlButton = findViewById(R.id.openUrl1Button)
        sourceImageView = findViewById(R.id.sourceImage1ImageView)
    }

    private fun initPresenter() {
        OtherInfoInjector.initGraph(this)
        presenter = OtherInfoInjector.presenter
    }

    private fun observerPresenter() {
        presenter.cardObservable.subscribe {
            artistBiography -> updateUI(artistBiography)
        }
    }

    private fun getArtistCardAsync() {
        Thread {
            getArtistCard()
        }.start()
    }

    private fun getArtistCard() {
        val artistName = getArtistName()
        presenter.updateCard(artistName)
    }

    private fun getArtistName() =
        intent.getStringExtra(ARTIST_NAME_EXTRA) ?: throw Exception("Missing artist name")

    private fun updateUI(uiState: CardUiState){
        runOnUiThread {
            updateOpenUrlButton(uiState.url)
            updateLastFMlLogo(uiState.imageUrl)
            updateCardText(uiState.contentHtml)
        }
    }

    private fun updateOpenUrlButton(url: String) {
        openUrlButton.setOnClickListener{
            navigateToUrl(url)
        }
    }

    private fun updateLastFMlLogo(url: String) {
        Picasso.get().load(url).into(sourceImageView)
    }

    private fun navigateToUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(url))
        startActivity(intent)
    }

    private fun updateCardText(infoHtml: String) {
        cardContentTextView.text = Html.fromHtml(infoHtml)
    }

    companion object {
        const val ARTIST_NAME_EXTRA = "artistName"
    }
}