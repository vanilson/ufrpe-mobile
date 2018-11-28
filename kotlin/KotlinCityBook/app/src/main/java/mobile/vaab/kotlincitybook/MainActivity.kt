package mobile.vaab.kotlincitybook

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val artNameArray = ArrayList<String>()
        val artImageArray = ArrayList<Bitmap>()

        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,artNameArray)
        listView.adapter = arrayAdapter

        try {

            val database = this.openOrCreateDatabase("CitiesDB", Context.MODE_PRIVATE,null)
            database.execSQL("CREATE TABLE IF NOT EXISTS city (name VARCHAR, image BLOB)")

            val cursor = database.rawQuery("SELECT * FROM city",null)

            val nameIx = cursor.getColumnIndex("name")
            val imageIx = cursor.getColumnIndex("image")

            if (cursor.getCount() > 0)
            {
                cursor.moveToFirst();

                do {

                    artNameArray.add(cursor.getString(nameIx))

                    val byteArray = cursor.getBlob(imageIx)
                    val image = BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)

                    artImageArray.add(image)

                } while (cursor.moveToNext());

                arrayAdapter.notifyDataSetChanged()
            }

            cursor?.close()


        } catch (e: Exception) {
            e.printStackTrace()
        }

        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->

            val intent = Intent(applicationContext,Main2Activity::class.java)
            intent.putExtra("name",artNameArray[i])
            intent.putExtra("action","details")

            Globals.chosenImage = artImageArray[i]

            startActivity(intent)

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_city,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.add_city) {

            val intent = Intent(applicationContext, Main2Activity::class.java)
            intent.putExtra("action","add")
            startActivity(intent)

        }

        return super.onOptionsItemSelected(item)
    }

}
