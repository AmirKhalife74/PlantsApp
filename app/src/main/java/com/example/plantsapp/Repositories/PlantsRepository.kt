package com.example.plantsapp.Repositories

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.plantsapp.Data.RoomDb.DAO
import com.example.plantsapp.Data.Plant
import com.example.plantsapp.Data.Remote.Api
import com.example.plantsapp.Utils.internetCheck

class PlantsRepository(private val dao: DAO, private val context: Context, private val api: Api) {
    suspend fun insertPlant(plant: Plant) {
        dao.insert(plant)
    }

    suspend fun deletePlant(plant: Plant) {
        dao.delete(plant)
    }

    fun getAllPlants(): List<Plant>{
        var plantList: List<Plant>
//        if (internetCheck(context) {
//                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
//            }) {
//            //plantList = api.getAllPlants()
//
//        } else {
            plantList = dao.getAllPlants()
        if (plantList.size == 0) {
            setSampleData().forEach { data ->
                dao.insert(data)
            }
            plantList = dao.getAllPlants()
        }

        return plantList
    }

    suspend fun getPlantsByName(plantName: String): Plant? {
        return dao.getPlantByName(plantName)
    }
    private fun setSampleData():MutableList<Plant>
    {
        val apartmentPlants = mutableListOf(
            Plant(
                id = 1,
                name = "Snake Plant",
                desc = "Easy to care for and hardy plant, thrives in low light.",
                fullName = "Sansevieria trifasciata",
                picture = "https://example.com/snake_plant.jpg",
                temp = 20,
                difficulty = 2,
                light = 3,
                pot = "Ceramic",
                water = "Low",
                price = 20,
                height = 60 // Height in cm
            ),
            Plant(
                id = 2,
                name = "Spider Plant",
                desc = "Great for beginners and purifies air.",
                fullName = "Chlorophytum comosum",
                picture = "https://example.com/spider_plant.jpg",
                temp = 22,
                difficulty = 1,
                light = 3,
                pot = "Plastic",
                water = "Moderate",
                price = 15,
                height = 30 // Height in cm
            ),
            Plant(
                id = 3,
                name = "Pothos",
                desc = "Trailing plant that's very forgiving and easy to grow.",
                fullName = "Epipremnum aureum",
                picture = "https://example.com/pothos.jpg",
                temp = 20,
                difficulty = 1,
                light = 2,
                pot = "Hanging",
                water = "Moderate",
                price = 18,
                height = 25 // Height in cm
            ),
            Plant(
                id = 4,
                name = "ZZ Plant",
                desc = "Low maintenance and thrives in low light conditions.",
                fullName = "Zamioculcas zamiifolia",
                picture = "https://example.com/zz_plant.jpg",
                temp = 20,
                difficulty = 2,
                light = 2,
                pot = "Plastic",
                water = "Low",
                price = 30,
                height = 45 // Height in cm
            ),
            Plant(
                id = 5,
                name = "Peace Lily",
                desc = "Beautiful flowering plant that enjoys shade and helps purify air.",
                fullName = "Spathiphyllum spp.",
                picture = "https://example.com/peace_lily.jpg",
                temp = 18,
                difficulty = 3,
                light = 2,
                pot = "Decorative",
                water = "Moderate",
                price = 30,
                height = 50 // Height in cm
            ),
            Plant(
                id = 6,
                name = "Aloe Vera",
                desc = "Succulent with medicinal properties, requires minimal care.",
                fullName = "Aloe barbadensis miller",
                picture = "https://example.com/aloe_vera.jpg",
                temp = 24,
                difficulty = 2,
                light = 4,
                pot = "Terracotta",
                water = "Low",
                price = 25,
                height = 40 // Height in cm
            ),
            Plant(
                id = 7,
                name = "Fiddle Leaf Fig",
                desc = "Large, attractive leaves; loves bright, indirect light.",
                fullName = "Ficus lyrata",
                picture = "https://example.com/fiddle_leaf_fig.jpg",
                temp = 21,
                difficulty = 4,
                light = 5,
                pot = "Ceramic",
                water = "Moderate",
                price = 60,
                height = 150 // Height in cm
            ),
            Plant(
                id = 8,
                name = "Rubber Plant",
                desc = "Tall plant with glossy leaves, does well in bright light.",
                fullName = "Ficus elastica",
                picture = "https://example.com/rubber_plant.jpg",
                temp = 21,
                difficulty = 3,
                light = 4,
                pot = "Ceramic",
                water = "Moderate",
                price = 40,
                height = 120 // Height in cm
            ),
            Plant(
                id = 9,
                name = "Orchid",
                desc = "Exotic flowers, requiring specific care but rewarding beauty.",
                fullName = "Orchidaceae",
                picture = "https://example.com/orchid.jpg",
                temp = 22,
                difficulty = 4,
                light = 3,
                pot = "Specialized",
                water = "Moderate",
                price = 50,
                height = 50 // Height in cm
            ),
            Plant(
                id = 10,
                name = "Chinese Evergreen",
                desc = "Hardy plant that thrives in low light and is easy to care for.",
                fullName = "Aglaonema",
                picture = "https://example.com/chinese_evergreen.jpg",
                temp = 20,
                difficulty = 2,
                light = 2,
                pot = "Plastic",
                water = "Moderate",
                price = 25,
                height = 45 // Height in cm
            ),
            Plant(
                id = 11,
                name = "Boston Fern",
                desc = "Lush foliage, loves humidity, perfect for bathrooms.",
                fullName = "Nephrolepis exaltata",
                picture = "https://example.com/boston_fern.jpg",
                temp = 18,
                difficulty = 4,
                light = 3,
                pot = "Hanging",
                water = "High",
                price = 25,
                height = 60 // Height in cm
            ),
            Plant(
                id = 12,
                name = "Calathea",
                desc = "Known for its stunning leaf patterns, enjoys humidity.",
                fullName = "Calathea spp.",
                picture = "https://example.com/calathea.jpg",
                temp = 22,
                difficulty = 3,
                light = 3,
                pot = "Decorative",
                water = "Moderate",
                price = 35,
                height = 50 // Height in cm
            ),
            Plant(
                id = 13,
                name = "Dracaena",
                desc = "Stylish plant with long leaves, tolerant of neglect.",
                fullName = "Dracaena spp.",
                picture = "https://example.com/dracaena.jpg",
                temp = 20,
                difficulty = 2,
                light = 3,
                pot = "Plastic",
                water = "Low",
                price = 30,
                height = 100 // Height in cm
            ),
            Plant(
                id = 14,
                name = "Cast Iron Plant",
                desc = "Extremely hardy plant, can tolerate neglect and low light.",
                fullName = "Aspidistra elatior",
                picture = "https://example.com/cast_iron_plant.jpg",
                temp = 18,
                difficulty = 1,
                light = 2,
                pot = "Ceramic",
                water = "Low",
                price = 22,
                height = 50 // Height in cm
            ),
            Plant(
                id = 15,
                name = "Parlor Palm",
                desc = "Low light palm, adds a tropical touch to your home.",
                fullName = "Chamaedorea elegans",
                picture = "https://example.com/parlor_palm.jpg",
                temp = 18,
                difficulty = 2,
                light = 2,
                pot = "Plastic",
                water = "Moderate",
                price = 28,
                height = 120 // Height in cm
            )
        )
        return apartmentPlants
    }

}