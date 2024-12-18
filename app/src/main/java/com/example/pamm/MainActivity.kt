package com.example.pamm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pamm.ui.theme.PammTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PammTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current

    val activities = listOf(
        ActivityItem("Committed changes", Icons.Default.Commit, 22),
        ActivityItem("Comment count", Icons.Default.Comment, 15),
        ActivityItem("Merged pull requests", Icons.Default.CallMerge, 8),
        ActivityItem("Closed pull requests", Icons.Default.Close, 3)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.man_person_icon),
                contentDescription = null,
                modifier = Modifier.size(72.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Kajetan Deja",
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 24.sp
                )
                Text(
                    text = "Git statistics",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF5E5676)
                )
            }
        }
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Text(
            text = "Recent Activities",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 8.dp)
        )

        ActivityList(activities)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                Toast.makeText(context, "Well done!", Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6E5D9C)),
            modifier = Modifier
                .width(400.dp),
            shape = CircleShape
        ) {
            Text("Display message", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ActivityList(activities: List<ActivityItem>) {
    Column {
        activities.forEach { activity ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(color = Color(0xFF5E5676), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = activity.icon,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(activity.name, style = MaterialTheme.typography.bodyMedium)
                }
                Text(activity.count.toString(), color = Color(0xFF6E5D9C))
            }
        }
    }
}

data class ActivityItem(val name: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val count: Int)

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    PammTheme {
        MainScreen()
    }
}
