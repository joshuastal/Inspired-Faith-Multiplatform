package com.myapp.inspiredfaithmultiplatform.QuoteStuff

import com.myapp.inspiredfaithmultiplatform.QuoteStuff.Quote
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

class QuoteUtil {
    val db = Firebase.firestore

    suspend inline fun <reified T : Any> getQuotesFromFirestore(): List<T> {
        return try {
            db.collection("Quotes")
                .get()
                .documents
                .map { it.data<T>() }
                // .map = go through every item in the list and perform the operation then put the result into a new list
                // { it.data<T>() }
                //      it = the current item
                //      .data<T>() = convert it to T
        } catch (e: Exception) {
            println("Firestore Test Error: ${e.message}")
            emptyList()
        }
    }

    /*
     suspend = asynchronous because this function takes an undetermined amount of time to finish
     inline = instead of jumping to where this function is in memory, it's literally placed in the place it's called
              this is required to use reified
     <reified T: Any> ->
      T = Type Parameter (it is a placeholder name, it stands for TYPE, meaning this func can return a list of any TYPE)
      : Any = T must be a real object, not a null
      reified = tells the compiler to remember what object type T is (in this case it is a quote)
                without this, T will not be remembered. without this they are deleted at runtime

     we could just make this explicitly typed as Quote, but this is better practice, and we can easily
     copy this if we are to add any future databases or use this in another project

     just don't forget to add a string parameter for the collectionPath in the function header if we do so
     and to replace "Quotes" with that parameter
     */
}