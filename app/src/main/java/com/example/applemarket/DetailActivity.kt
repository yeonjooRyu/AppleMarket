package com.example.applemarket

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityDetailBinding

    private var isLike = false

    private val item: SaleItem? by lazy {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableExtra(Constants.ITEM_OBJECT, SaleItem::class.java)
        } else {
            intent.getParcelableExtra<SaleItem>(Constants.ITEM_OBJECT)
        }
    }

    private val itemPosition: Int by lazy {
        intent.getIntExtra(Constants.ITEM_INDEX, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("yjRyu", "itemPosition = $itemPosition")

        binding.ivItemImage.setImageDrawable(item?.let {
            ResourcesCompat.getDrawable(
                resources,
                it.Image,
                null
            )
        })

        binding.tvSellerName.text = item?.SellerName
        binding.tvSellerAddress.text = item?.Address
        binding.tvItemTitle.text = item?.ItemTitle
        binding.tvItemDetail.text = item?.ItemDetail
        binding.tvItemDetailPrice.text = DecimalFormat("#,###").format(item?.Price) + "원"

        isLike = item?.isLike == true

        binding.ivDetailLike.setImageResource(if(isLike) {R.drawable.heart}else{R.drawable.heart})

        binding.ivBack.setOnClickListener {
            exit()
        }

        binding.llDetailLike.setOnClickListener {
            if(!isLike){
                binding.ivDetailLike.setImageResource(R.drawable.heart2)
                Snackbar.make(binding.constraintLayout, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                isLike = true
            }else {
                binding.ivDetailLike.setImageResource(R.drawable.heart)
                isLike = false
            }
        }
    }

    fun exit(){
        val intent = Intent(this, MainActivity::class.java).apply{
            putExtra("itemIndex", itemPosition)
            putExtra("isLike", isLike)
        }
        setResult(RESULT_OK, intent)
        if(!isFinishing) finish()
    }

    override fun onBackPressed() {
        exit()
    }
}