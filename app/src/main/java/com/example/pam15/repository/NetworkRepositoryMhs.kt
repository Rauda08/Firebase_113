package com.example.pam15.repository

import com.example.pam15.model.Mahasiswa
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkRepositoryMhs(
    private val firestore: FirebaseFirestore
) : RepositoryMhs {
    override suspend fun insertMhs(mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

    override fun getAllMahasiswa(): Flow<List<Mahasiswa>> = callbackFlow {
        //membuka collection dri firestore
        val mhsCollection = firestore.collection("Mahasiswa")
            .orderBy("nim", Query.Direction.ASCENDING)
            .addSnapshotListener {
                value, error ->
                if (value != null) {
                    val mhsList = value.documents.mapNotNull {
                        //convert dri document firestore ke data class
                        it.toObject(Mahasiswa::class.java)!!
                    }
                    //fungsi utk mengirim collectionn ke data class
                    trySend(mhsList)
                }
            }
        awaitClose{
            //close collesction dri firestore
            mhsCollection.remove()
        }
    }

    override fun getMhs(nim: String): Flow<Mahasiswa> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMhs(mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

    override suspend fun updateMhs(mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

}
