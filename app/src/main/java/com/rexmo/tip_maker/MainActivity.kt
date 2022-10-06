package com.rexmo.tip_maker
import com.rexmo.tip_maker.ui.theme.Tip_makerTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tip_makerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TxtInput()
                }
            }
        }
    }
}

@Composable
fun TxtInput() {
    val pink = Color(0xFFEE8FB8)
    val cyan = Color(0xFF86D7FC)
    val black1 = Color(0xFF262727)
    var input1 by remember { mutableStateOf("") }
    var input2 by remember { mutableStateOf("") }
    var input3 by remember { mutableStateOf("") }





    //outer column
    Column(modifier = Modifier
        .background(black1)
        .padding(10.dp)
        .fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

            val img= painterResource(id =R.drawable.tip_maker )
            Image(modifier = Modifier
                .width(150.dp)
                .height(150.dp), painter = img, contentDescription ="Tip Demo" )


        }
        Spacer(modifier = Modifier.height(100.dp))
/*

        .graphicsLayer {
                shadowElevation = 10.dp.toPx()
                shape = CutCornerShape(40.dp)
                clip = true
            }



*/

        Column(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(pink)
            .padding(10.dp)
            .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {


            Text("Bill", fontSize = 20.sp)
            TextField(modifier = Modifier
                .clip(CircleShape)
                .background(Color.White), value = input1, onValueChange = {input1=it}, placeholder ={ Text("Enter Total amount", color = Color.Black)},
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number), textStyle = TextStyle(color = Color.Black, fontSize = 20.sp)
            )
            Text("Tip %", fontSize = 20.sp )
            TextField(modifier = Modifier
                .clip(CircleShape)
                .background(Color.White),value = input2, onValueChange = {input2=it}, placeholder ={ Text("Enter Tip %", color = Color.Black)},
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number), textStyle = TextStyle(color = Color.Black, fontSize = 20.sp))
            Text("Number of people", fontSize = 20.sp)
            TextField(modifier = Modifier
                .clip(CircleShape)
                .background(Color.White),value = input3, onValueChange = {input3=it}, placeholder ={ Text("Enter No. of people", color = Color.Black)},
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number), textStyle = TextStyle(color = Color.Black, fontSize = 20.sp))


        }
        Spacer(modifier = Modifier.height(50.dp))


        Column(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(cyan)
            .padding(10.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                val amount:Float=conv(input1.trim())
                val tip:Float=conv(input2.trim())
                val people:Int=people(input3.trim())
                val tipPercentage:Float=(tip/100)*amount
                val totalAmount=amount+tipPercentage
                val amountPerPerson=totalAmount/people
                val tipPerPerson=tipPercentage/people
                Text(text = "Amount per person is $amountPerPerson")
                Text(text = "tip per person is $tipPerPerson")

            }


        }

    }
}

fun conv(i:String):Float{
    return if(i=="") 0f else i.toFloat()
}
fun people(i:String):Int{
    return if(i=="") 0 else i.toInt()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Tip_makerTheme {
        TxtInput()
    }
}