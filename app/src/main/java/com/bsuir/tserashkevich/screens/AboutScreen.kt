package com.bsuir.tserashkevich.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bsuir.tserashkevich.R
import com.bsuir.tserashkevich.ui.theme.DarkGrey
import com.bsuir.tserashkevich.ui.theme.White


@Composable
fun AboutScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DarkGrey),
            content = {
                item { Version() }
                item { Email() }
                item { GitHub() }
                item { CreatorInfo() }
                item { Rating() }
            },
        )
    }
}




@Composable
fun Version(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .height(80.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = stringResource(R.string.version),
            tint = White,
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 15.dp)
        )
        Column {
            Text(text = stringResource(R.string.version), color = White, fontWeight = FontWeight.W600)
            Text(text = stringResource(R.string.numer_version), color = White, fontSize = 12.sp)
        }
    }
}


@Composable
fun Email(){
    val clipboardManager: androidx.compose.ui.platform.ClipboardManager = LocalClipboardManager.current

    var context = LocalContext.current

    var urlEmail = stringResource(id = R.string.url_email)
    var EmailIntent = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse(urlEmail))
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .height(80.dp)
            .clickable { clipboardManager.setText(AnnotatedString("tegor2003@gmail.com")) }
    ) {
        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = stringResource(R.string.email),
            tint = White,
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 15.dp)
        )
        Column {
            Text(text = stringResource(R.string.email), color = White, fontWeight = FontWeight.W600)
            Text(text = stringResource(R.string.emal_adress), color = White)
        }
    }
}

@Composable
fun GitHub(){
    var context = LocalContext.current

    var urlGit = stringResource(id = R.string.url_github)
    var GitIntent = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse(urlGit))
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .height(80.dp)
            .clickable { context.startActivity(GitIntent) }
    ) {
        val image = R.drawable.github_svgrepo_com

        Icon(
            imageVector = ImageVector.vectorResource(image),
            contentDescription = stringResource(R.string.github),
            tint = White,
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 15.dp)
                .size(25.dp)
        )

        Column {
            Text(text = stringResource(R.string.github), color = White, fontWeight = FontWeight.W600)
            Text(text = stringResource(R.string.git_name), color = White)
        }
    }
}


@Composable
fun CreatorInfo(){
    var context = LocalContext.current

    var urlCreator = stringResource(id = R.string.url_creator)
    var CreatorIntent = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse(urlCreator))
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .height(80.dp)
            .clickable { context.startActivity(CreatorIntent) }

    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = stringResource(R.string.creator_info),
            tint = White,
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 15.dp)
                .size(25.dp)
        )

        Column {
            Text(text = stringResource(R.string.creator_name), color = White, fontWeight = FontWeight.W600)
        }
    }
}


@Composable
fun Rating(){
    var context = LocalContext.current

    var urlMarket = stringResource(id = R.string.url_playmarket)
    var MarketIntent = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse(urlMarket))
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .height(80.dp)
            .clickable { context.startActivity(MarketIntent) }
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = stringResource(R.string.rating),
            tint = White,
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 15.dp)
                .size(25.dp)
        )

        Column {
            Text(text = stringResource(R.string.rate_us), color = White, fontWeight = FontWeight.W600)
            Text(text = stringResource(R.string.very_impotant_string), color = White)
        }
    }
}

