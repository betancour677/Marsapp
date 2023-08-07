package com.example.marsapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marsapp.Model.MarsApiClass
import com.example.marsapp.Model.MarsDao
import com.example.marsapp.Model.RetrofitClient
import retrofit2.Call
import retrofit2.Response
/************ CUANDO QUIERO CREAR LA BASE DE DATOS INSERTO EL DAO EN CONSTRUCTOR*************/
class MarsRepository (private val marsDao: MarsDao) {


    // 1 llamar al método de conexion
    private val retrofitClient = RetrofitClient.getRetrofit()
    //2 HACE REFERENCIA AL POJO Y LA RESPUESTA QUE VAMOS A RECIBIR
    val dataFromInternet = MutableLiveData<List<MarsApiClass>>()


    // vieja confiable
    fun fetchDataMars(): LiveData<List<MarsApiClass>> {
        Log.d("REPO", "VIEJA CONFIABLE")
        retrofitClient.fetchMarsData().enqueue(object : retrofit2.Callback<List<MarsApiClass>> {
            override fun onResponse(
                call: Call<List<MarsApiClass>>,
                response: Response<List<MarsApiClass>>
            ) {

                when (response.code()) {

                    in 200..299 -> dataFromInternet.value = response.body()
                    in 300..301 -> Log.d("REPO", "${response.code()} ---${response.errorBody()}")
                    else -> Log.d("E", "${response.code()} ---${response.errorBody()}")
                }


            }

            override fun onFailure(call: Call<List<MarsApiClass>>, t: Throwable) {
                Log.e("Error", " ${t.message}")
            }

        })

        return dataFromInternet


    }


    // nueva Forma con coroutinas

    suspend fun fetchDataFromInternetCoroutines() {
        try {
            val response = retrofitClient.fetchMarsDataCoroutines()
            when (response.code()) {
                //  in 200..299 -> dataFromInternet.value = response.body()
                in 200..299 -> response?.body().let {
                    if (it != null) {
                        // esta insertando en la base de datos Luego que creo la base de datos
                        marsDao.insertTerrain(it)
                    }
                }

                in 300..301 -> Log.d("REPO", "${response.code()} --- ${response.errorBody()}")
                else -> Log.d("REPO", "${response.code()} --- ${response.errorBody().toString()}")
            }
        } catch (t: Throwable) {
            Log.e("REPO", "${t.message}")
        }
    }


    /********************+IMPLEMENTACIÓN DE DAO, LUEGO QUE CREO BASE DE DATOS*********/
    /************ si quiero insertar sin base de datos no es necesario *********/


    fun  getTerrainByid(id:String) : LiveData<MarsApiClass>{
        return getTerrainByid(id)
    }

    // listado de terrenos
    val lisTAllMars : LiveData<List<MarsApiClass>> =marsDao.getAllTerrains()


    // insertar un terreno
    suspend fun inserTerrain(mars: MarsApiClass) {
        marsDao.insertTerrain(mars)
    }

    // actualizar un terreno

    suspend fun updateTerrain(mars: MarsApiClass) {
        marsDao.updateTerrain(mars)
    }

    // elimina terrenos
    suspend fun deleteAll() {
        marsDao.deleteAll()
    }
    //traer terreno por id

    fun getTerrain(id:Int):LiveData<MarsApiClass>{
        return  marsDao.getMarsId(id)
    }}