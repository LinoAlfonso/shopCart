package com.lino.shopcart.view.adapater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lino.shopcart.R
import com.lino.shopcart.databinding.ItemMovieShopcartBinding
import com.lino.shopcart.models.Movie
import com.lino.shopcart.utils.Constants.Companion.ADD_CART
import com.lino.shopcart.utils.Constants.Companion.DELETE_ITEM
import com.lino.shopcart.utils.Constants.Companion.REMOVE_CART
import com.lino.shopcart.utils.bindImageUrl
import kotlinx.android.synthetic.main.buttoms_actions_cart.view.*
import kotlinx.android.synthetic.main.item_movie_shopcart.view.*

class ShopCartAdapter (val shopCartListener: ShopCartListener) : RecyclerView.Adapter<ShopCartAdapter.ItemCartViewHolder>() {

    var listItemsMoviesCart = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemCartViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_shopcart,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ItemCartViewHolder, position: Int) {
        holder.bind(listItemsMoviesCart[position],shopCartListener)

    }

    override fun getItemCount() = listItemsMoviesCart.size

    class ItemCartViewHolder(val itemMovieShopcartBinding: ItemMovieShopcartBinding) :
        RecyclerView.ViewHolder(itemMovieShopcartBinding.root){
        fun bind(item: Movie,shopCartListener: ShopCartListener){
            itemMovieShopcartBinding.movie = item
            itemView.imgProductCart.bindImageUrl(
                url = "https://image.tmdb.org/t/p/w500/"+item.backdropPath,
                placeholder = R.drawable.ic_broken_image,
                errorPlaceholder = R.drawable.ic_broken_image
            )

            itemView.btnAdd.setOnClickListener {
                shopCartListener.clickListenerMovie(item,ADD_CART)
            }
            itemView.btnRemove.setOnClickListener {
                shopCartListener.clickListenerMovie(item, REMOVE_CART)
            }
            itemView.btnDeleteItem.setOnClickListener {
                shopCartListener.clickListenerMovie(item, DELETE_ITEM)
            }

        }
    }

    fun updateData(data: List<Movie>) {
        listItemsMoviesCart.clear()
        listItemsMoviesCart.addAll(data)
        notifyDataSetChanged()
    }
}