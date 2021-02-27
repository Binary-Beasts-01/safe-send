package com.example.safesend.Utility
import android.annotation.SuppressLint
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CursorAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.example.safesend.R
import java.lang.reflect.Array.getLong


/*
 * Defines an array that contains column names to move from
 * the Cursor to the ListView.
 */
@SuppressLint("InlinedApi")
private val FROM_COLUMNS: Array<String> = arrayOf(
    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
)

@SuppressLint("InlinedApi")
private val PROJECTION: Array<out String> = arrayOf(
    /*
     * The detail data row ID. To make a ListView work,
     * this column is required.
     */
    ContactsContract.Data._ID,
    // The primary display name
    ContactsContract.Data.DISPLAY_NAME_PRIMARY,
    // The contact's _ID, to construct a content URI
    ContactsContract.Data.CONTACT_ID,
    // The contact's LOOKUP_KEY, to construct a content URI
    ContactsContract.Data.LOOKUP_KEY
)

private val SELECTION: String =
    /*
     * Searches for an email address
     * that matches the search string
     */
    "${ContactsContract.CommonDataKinds.Email.ADDRESS} LIKE ? AND " +
            /*
             * Searches for a MIME type that matches
             * the value of the constant
             * Email.CONTENT_ITEM_TYPE. Note the
             * single quotes surrounding Email.CONTENT_ITEM_TYPE.
             */
            "${ContactsContract.Data.MIMETYPE } = '${ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE}'"

private var searchString: String? = null
private val selectionArgs: Array<String> = arrayOf("")

/*
 * Defines an array that contains resource ids for the layout views
 * that get the Cursor column contents. The id is pre-defined in
 * the Android framework, so it is prefaced with "android.R.id"
 */
private val TO_IDS: IntArray = intArrayOf(android.R.id.text1)
// The column index for the _ID column
private const val CONTACT_ID_INDEX: Int = 0
// The column index for the CONTACT_KEY column
private const val CONTACT_KEY_INDEX: Int = 1

class ComposeSmsFragment: Fragment(), LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {
    // Define global mutable variables
    // Define a ListView object
    lateinit var contactsList: ListView
    // Define variables for the contact the user selects
    // The contact's _ID value
    var contactId: Long = 0
    // The contact's LOOKUP_KEY
    var contactKey: String? = null
    // A content URI for the selected contact
    var contactUri: Uri? = null
    // An adapter that binds the result Cursor to the ListView
    private val cursorAdapter: SimpleCursorAdapter? = null
    // A UI Fragment must inflate its View

    override fun onCreate(savedInstanceState: Bundle?) {
        // Always call the super method first
        super.onCreate(savedInstanceState)

        // Initializes the loader
        loaderManager.initLoader(0, null, this)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the fragment layout
        return inflater.inflate(R.layout.contact_list_fragment, container, false)
    }

    override fun onItemClick(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        // Get the Cursor
        val cursor: Cursor? = (parent.adapter as? SimpleCursorAdapter)?.cursor?.apply {
            // Move to the selected contact
            moveToPosition(position)
            // Get the _ID value
            contactId = this.getLong(CONTACT_ID_INDEX)
            // Get the selected LOOKUP KEY
            contactKey = this.getString(CONTACT_KEY_INDEX)
            // Create the contact's content Uri
            contactUri = ContactsContract.Contacts.getLookupUri(contactId, contactKey)
            /*
             * You can use contactUri as the content URI for retrieving
             * the details for a contact.
             */
        }
    }

    override fun onCreateLoader(loaderId: Int, args: Bundle?): Loader<Cursor> {
        /*
         * Appends the search string to the base URI. Always
         * encode search strings to ensure they're in proper
         * format.
         */
        val contentUri: Uri = Uri.withAppendedPath(
            ContactsContract.Contacts.CONTENT_FILTER_URI,
            Uri.encode(searchString)
        )
        // Starts the query
        return activity?.let {
            CursorLoader(
                it,
                contentUri,
                PROJECTION,
                null,
                null,
                null
            )
        } ?: throw IllegalStateException()
    }

    override fun onLoadFinished(loader: Loader<Cursor>, cursor: Cursor) {
        // Put the result Cursor in the adapter for the ListView
        cursorAdapter?.swapCursor(cursor)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        // Delete the reference to the existing Cursor
        cursorAdapter?.swapCursor(null)
    }



}