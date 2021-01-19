package com.desarrollodroide.simplecleancode.util

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

object RecyclerViewMatchers {

    @JvmStatic
    fun hasItemCount(itemCount: Int): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(
                RecyclerView::class.java) {

            override fun describeTo(description: Description) {
                description.appendText("has $itemCount items")
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                return view.adapter?.itemCount == itemCount
            }
        }
    }

    fun <VH : RecyclerView.ViewHolder> hasHolderItemAtPosition(position: Int, matcher: Matcher<VH>): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has holder item: ")
                matcher.describeTo(description)
                description.appendText(" at position: $position")
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                return view.adapter?.let {
                    val type = it.getItemViewType(position)
                    val holder = it.createViewHolder(view, type)
                    it.onBindViewHolder(holder, position)
                    return matcher.matches(holder)
                } ?: false
            }
        }
    }

    fun withTextViewText(textViewID: Int, text: String): Matcher<RecyclerView.ViewHolder> {
        return object :
                BoundedMatcher<RecyclerView.ViewHolder, RecyclerView.ViewHolder>(
                        RecyclerView.ViewHolder::class.java
                ) {
            override fun matchesSafely(item: RecyclerView.ViewHolder): Boolean {
                return (item.itemView.findViewById<TextView>(textViewID)).text == text
            }

            override fun describeTo(description: Description) {
                description.appendText("Text expected: $text")
            }
        }
    }
}