package com.example.tareafragmentos.Vista

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.tareafragmentos.Fragments.ListaAlumnosFrag
import com.example.tareafragmentos.Modelo.*
import com.example.tareafragmentos.R
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    var gestorGson = GestorGson()
    var spinnerSelection = "BBDD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var gson = gestorGson.readGson(this)
        gson.alumnos.forEach{
            DataRepository(this).insertAlumno(it)
        }
        gson.profesores.forEach{
            System.out.println(it.toString())
            DataRepository(this).insertProfesor(it)
        }
        var listaProfesores = DataRepository(this).getProfesor()
        var listaAlumnos = DataRepository(this).getAlumnos()


        txtProfesores.text = listaProfesores?.get(0).toString() + "\n" + listaProfesores?.get(1).toString()
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, gson.asignaturas)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                if (gson.asignaturas[position].equals("BBDD")){
                    spinnerSelection = "BBDD"
                    txtProfesores.text = listaProfesores?.get(1).toString()
                }
                if (gson.asignaturas[position].equals("programacion")){
                    txtProfesores.text = listaProfesores?.get(0).toString()
                    spinnerSelection = "programacion"
                }
                (this@MainActivity.fragment as ListaAlumnosFrag).actualizar(spinnerSelection)
                Toast.makeText(applicationContext, gson.asignaturas[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("CUIDADOOO!!!!")
        builder.setMessage("¿Está seguro que quiere salir?")

        builder.setPositiveButton("Si") { dialog, _ -> finish() }
        builder.setNegativeButton("No") { dialog, which -> }

        builder.show()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("spinner", spinnerSelection)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        spinnerSelection = savedInstanceState.getString("spinner")!!
    }
}