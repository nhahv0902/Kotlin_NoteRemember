package com.nhahv.noteremember.ui.loadpicture.model

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import java.lang.ref.WeakReference

/**
 * Created by nhahv on 9/30/17.
 */
class PictureLoader(context: Context) {
  private val SELECTION_ARGS = arrayOf("image/jpeg", "image/png")
  private val MIME_TYPE = MediaStore.Images.Media.MIME_TYPE
  private val SELECTION = "$MIME_TYPE=? or $MIME_TYPE=?"
  private val IMAGE_PROJECTION = arrayOf(MediaStore.Images.Media.DATA,
      MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.DATE_ADDED,
      MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME)

  val reference: WeakReference<Context> = WeakReference(context)

  fun loadPictures(): ArrayList<Folder> {
    val folders: ArrayList<Folder> = ArrayList()
    if (reference.get() == null) return folders

    val context : Context = reference.get()!!

    val cursor: Cursor? = context.contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION, SELECTION,
        SELECTION_ARGS, IMAGE_PROJECTION[2] + " DESC", null)

    if (cursor == null || !cursor.moveToFirst()) return folders
    val indexPath = cursor.getColumnIndex(IMAGE_PROJECTION[0])
    val indexFolder = cursor.getColumnIndex(IMAGE_PROJECTION[3])
    var folderName: String?
    var imagePath: String?

    val hashFolder: HashMap<String, Folder> = HashMap()

    while (!cursor.isAfterLast) {
      folderName = cursor.getString(indexFolder)
      imagePath = cursor.getString(indexPath)

      if (!hashFolder.containsKey(folderName)) {
        hashFolder.put(folderName, Folder(folderName, ArrayList()))
      }
      hashFolder[folderName]?.images?.add(imagePath)
      cursor.moveToNext()
    }
    cursor.close()
    for ((_, value) in hashFolder) {
      folders.add(value)
    }
    return folders
  }
}