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
    private lateinit var paneTextView: TextView
    private lateinit var openUrlButton: Button
    private lateinit var lastFMImageView: ImageView
    private lateinit var paneSourceInfo: TextView

    private lateinit var presenter: OtherInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_info)

        initViewProperties()
        initPresenter()

        observerPresenter()
        getArtistInfoAsync()
    }

    private fun initViewProperties() {
        paneTextView = findViewById(R.id.textPane1)
        openUrlButton = findViewById(R.id.openUrlButton1)
        lastFMImageView = findViewById(R.id.imageView1)
        paneSourceInfo = findViewById(R.id.infoSource)
    }

    private fun initPresenter() {
        OtherInfoInjector.initGraph(this)
        presenter = OtherInfoInjector.presenter
    }

    private fun observerPresenter() {
        presenter.artistBiographyObservable.subscribe {
            artistBiography -> updateUI(artistBiography)
        }
    }

    private fun getArtistInfoAsync() {
        Thread {
            getArtistInfo()
        }.start()
    }

    private fun getArtistInfo() {
        val artistName = getArtistName()
        presenter.getArtistInfo(artistName)
    }

    private fun getArtistName() =
        intent.getStringExtra(ARTIST_NAME_EXTRA) ?: throw Exception("Missing artist name")

    private fun updateUI(uiState: ArtistBiographyUiState){
        runOnUiThread {
            updateOpenUrlButton(uiState.articleUrl)
            updateLastFMlLogo(uiState.imageUrl)
            updateArticleText(uiState.infoHtml)
        }
    }

    private fun updateOpenUrlButton(url: String) {
        openUrlButton.setOnClickListener{
            navigateToUrl(url)
        }
    }

    private fun updateLastFMlLogo(url: String) {
        Picasso.get().load(url).into(lastFMImageView)
    }

    private fun navigateToUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(url))
        startActivity(intent)
    }

    private fun updateArticleText(infoHtml: String) {
        paneTextView.text = Html.fromHtml(infoHtml)
    }

    companion object {
        const val ARTIST_NAME_EXTRA = "artistName"
    }
}