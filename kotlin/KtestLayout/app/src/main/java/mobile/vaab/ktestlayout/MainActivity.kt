package mobile.vaab.ktestlayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var bestOption : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateMessageWithPreviousChoice(savedInstanceState)
    }

    private fun updateMessageWithPreviousChoice(savedInstanceState: Bundle?) {
        this.bestOption = savedInstanceState?.getInt("bestOption")
        updateMessage()
    }

    fun checkBestOption(view : View) {

        if (anyPriceIsEmpty()) {
            tvResult.text = ""
            this.bestOption = null

            Toast.makeText(applicationContext, getString(R.string.fillPrices), Toast.LENGTH_SHORT).show()
            return
        }


        var gasValue = tfGas.text.toString().toDouble();
        var ethanolValue = tfEthanol.text.toString().toDouble()

        this.bestOption =
                if ((gasValue * 0.7) < ethanolValue)
                    R.string.gasBestOption
                else
                    R.string.ethanolBestOption

        updateMessage()
    }

    private fun anyPriceIsEmpty(): Boolean {
        return tfGas.text.toString().isEmpty() || tfEthanol.text.toString().isEmpty()
    }

    private fun updateMessage() {

        when (this.bestOption) {
            R.string.gasBestOption -> tvResult.setText(getString(R.string.gasBestOption))
            R.string.ethanolBestOption -> tvResult.setText(getString(R.string.ethanolBestOption))
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        if (this.bestOption == null) {
            return
        }

        outState?.putInt("bestOption", this.bestOption!!)
    }
}
