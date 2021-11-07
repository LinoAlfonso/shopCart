package com.lino.shopcart.view.adapater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lino.shopcart.R
import com.lino.shopcart.databinding.ItemProductBinding
import com.lino.shopcart.models.Movie
import com.lino.shopcart.utils.Constants.Companion.ADD_CART
import com.lino.shopcart.utils.bindImageUrl
import kotlinx.android.synthetic.main.item_product.view.*

class MovieAdapter(val movieListener: MovieListener) : RecyclerView.Adapter<MovieAdapter.ProductGridViewHolder>() {

    var listItemsMovies = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductGridViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_product,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ProductGridViewHolder, position: Int) {
        holder.bind(listItemsMovies[position],position,movieListener)

    }

    override fun getItemCount() = listItemsMovies.size

    class ProductGridViewHolder(val itemProduct: ItemProductBinding) :
        RecyclerView.ViewHolder(itemProduct.root){
        fun bind(item: Movie,position: Int,movieListener: MovieListener){
            itemProduct.movie = item
            itemView.imgProduct.bindImageUrl(
                url = "https://image.tmdb.org/t/p/w500/"+item.backdropPath,
                placeholder = R.drawable.ic_broken_image,
                errorPlaceholder = R.drawable.ic_broken_image
            )

            itemView.movieId.setOnClickListener {
                movieListener.onMovieClicked(item,100)
            }

            itemView.btnAddCart.setOnClickListener {
                movieListener.onMovieClicked(item,ADD_CART)
            }

        }
        }

    fun updateData(data: List<Movie>) {
        listItemsMovies.clear()
        listItemsMovies.addAll(data)
        notifyDataSetChanged()
    }
}