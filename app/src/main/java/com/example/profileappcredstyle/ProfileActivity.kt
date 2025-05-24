package com.example.profileappcredstyle
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.profileappcredstyle.databinding.ActivityProfileBinding
class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        populateProfileData()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.findViewById<View>(R.id.button_support).setOnClickListener {
            Toast.makeText(this, "Support Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    // For the back arrow in toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetTextI18n") // For "₹" symbol
    private fun populateProfileData() {
        // Profile Header (already has placeholders, but you can set them)
        binding.imageProfile.setImageResource(R.drawable.asrith)
        binding.textName.text = "Asrith Behala"
        binding.textMemberSince.text = "member since Dec, 2020"
        binding.buttonEditProfile.setOnClickListener {
            Toast.makeText(this, "Edit Profile Clicked", Toast.LENGTH_SHORT).show()
        }


        // CRED Garage
        binding.iconGarage.setImageResource(R.drawable.directions_car)
        binding.textGarageTitle.text = "get to know your vehicles, inside out"
        binding.textGarageSubtitle.text = "CRED garage"
        (binding.iconGarage.parent as View).setOnClickListener {
            Toast.makeText(this, "CRED Garage Clicked", Toast.LENGTH_SHORT).show()
        }


        // Stats Section
        setupListItem(
            binding.itemCreditScore.root,
            R.drawable.credit_card,
            "credit score",
            "REFRESH AVAILABLE",
            "757",
            true // hasSubLabel
        )

        setupListItem(
            binding.itemLifetimeCashback.root,
            R.drawable.savings,
            "lifetime cashback",
            null,
            "₹3"
        )

        setupListItem(
            binding.itemBankBalance.root,
            R.drawable.account_balance_wallet,
            "bank balance",
            null,
            "check"
        )

        // Rewards & Benefits Section
        setupListItem(
            binding.itemCashbackBalance.root,
            R.drawable.savings,
            "cashback balance",
            null,
            "₹0"
        )
        setupListItem(
            binding.itemCoins.root,
            R.drawable.savings,
            "coins",
            null,
            "26,46,583"
        )
        setupListItem(
            binding.itemWinUpto.root,
            R.drawable.card_giftcard, // Your gift icon
            "win upto Rs 1000",
            "refer and earn", // Using sublabel for this
            null, // No main value to the right
            true,
            subLabelAsValue = true // Custom flag to adjust sublabel style
        )

        // Transactions & Support Section
        setupListItem(
            binding.itemAllTransactions.root,
            R.drawable.receipt_long, // Your transaction icon
            "all transactions",
            null,
            null // No value on the right, just arrow
        )
    }

    private fun setupListItem(
        itemView: View,
        iconRes: Int,
        label: String,
        subLabel: String?,
        value: String?,
        hasSubLabel: Boolean = subLabel != null,
        subLabelAsValue: Boolean = false
    ) {
        val itemIcon = itemView.findViewById<ImageView>(R.id.item_icon)
        val itemLabel = itemView.findViewById<TextView>(R.id.item_label)
        val itemSublabel = itemView.findViewById<TextView>(R.id.item_sublabel)
        val itemValue = itemView.findViewById<TextView>(R.id.item_value)
        val itemArrow = itemView.findViewById<ImageView>(R.id.item_arrow)


        itemIcon.setImageResource(iconRes)
        itemLabel.text = label

        if (hasSubLabel && subLabel != null) {
            itemSublabel.text = subLabel
            itemSublabel.visibility = View.VISIBLE
            if (subLabel == "REFRESH AVAILABLE") {
                itemSublabel.setTextColor(ContextCompat.getColor(this, R.color.cred_green_accent))
            } else if (subLabelAsValue) {
                itemSublabel.setTextColor(ContextCompat.getColor(this, R.color.cred_text_secondary)) // Grey for "refer and earn"
            }
            else {
                itemSublabel.setTextColor(ContextCompat.getColor(this, R.color.cred_text_secondary))
            }
        } else {
            itemSublabel.visibility = View.GONE
        }

        if (value != null) {
            itemValue.text = value
            itemValue.visibility = View.VISIBLE
            itemArrow.visibility = View.VISIBLE
        } else if (!subLabelAsValue) {
            itemValue.visibility = View.GONE

            itemArrow.visibility = View.VISIBLE

        } else {
            itemValue.visibility = View.GONE
            itemArrow.visibility = View.VISIBLE
        }


        itemView.setOnClickListener {
            Toast.makeText(this, "$label Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}