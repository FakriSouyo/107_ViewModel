package com.example.navigationwithdata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.navigationwithdata.data.dataform
import com.example.navigationwithdata.data.datasource.jenis
import com.example.navigationwithdata.data.datasource.status
import com.example.navigationwithdata.ui.theme.NavigationWithDataTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationWithDataTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background)
                {
                    TampilLayout()
                }
            }
        }
    }
}


@Composable
fun TampilLayout(
    modifier: Modifier = Modifier
){
    Card (
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ){
        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ){
            TampilFrom()
        }
    }
}

@Composable
fun SelectJK(
    options: List<String>,
    oneSelectionChanged: (String) -> Unit = {}
){
    var selectedValue by rememberSaveable { mutableStateOf("")}

    Column (modifier = Modifier.padding(16.dp)) {
        Text(
            text = "jenis kelamin:",
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        options.forEach{ item ->
            Row (
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        oneSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ){

                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        oneSelectionChanged(item)
                    }
                )
                Text(item)
            }

        }

    }

}

@Composable
fun Status(
    options: List<String>,
    oneSelectionChanged: (String) -> Unit = {}
){
    var selectedValue by rememberSaveable { mutableStateOf("")}

    Column (modifier = Modifier.padding(16.dp)) {
        Text(
            text = " status :",
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        options.forEach{ item ->
            Row (
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        oneSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        oneSelectionChanged(item)
                    }
                )
            }

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TampilFrom(cobaViewModel: CobaViewModel = viewModel()){
    var textNama by remember { mutableStateOf("") }
    var textTlp by remember { mutableStateOf("") }
    var textAlmt by remember { mutableStateOf("") }
    var texteml by remember { mutableStateOf("") }

    val context = LocalContext.current
    val dataForm: dataform
    val uiState by cobaViewModel.uiState.collectAsState()
    dataForm = uiState
    Text(
        text = "Register",
        fontSize = 25.sp,
        color = Color.Black,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )

    OutlinedTextField(
        value = textNama,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Nama Lengkap") },
        onValueChange = {
            textNama = it
        }
    )
    OutlinedTextField(
        value = textTlp,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Telephone") },
        onValueChange = {
            textTlp = it
        }
    )
    OutlinedTextField(
        value = texteml,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Email") },
        onValueChange = {
            texteml = it
        }
    )
    SelectJK(
        options = jenis.map { id -> context.resources.getString(id)},
        oneSelectionChanged = { cobaViewModel.setJenisK(it)})
    Status(
        options = status.map { id -> context.resources.getString(id)},
        oneSelectionChanged = { cobaViewModel.setJenisK(it)})
    OutlinedTextField(
        value = textAlmt,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Alamat") },
        onValueChange = {
            textAlmt = it
        }
    )


    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            cobaViewModel.insertData(textNama, textTlp,textAlmt,texteml, dataForm.sex)
        }
    ) { Text(
        text = stringResource(R.string.register),
        fontSize = 16.sp) }
    Spacer(
        modifier = Modifier.height(100.dp))
    TextHasil(
        namanya = cobaViewModel.namaUsr ,
        telponnya = cobaViewModel.noTlp,
        emailnya = cobaViewModel.email,
        alamatnya = cobaViewModel.Alamat,
        jenisnya = cobaViewModel.jenisKl,
        statusnya = cobaViewModel.status
    )
}


@Composable
fun TextHasil(namanya: String, telponnya: String, emailnya: String ,alamatnya:String, jenisnya: String, statusnya: String){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()

    ){
        Text(text = "Nama :" + namanya,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
        Text(text = "Telepon : " + telponnya,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
        Text(text = "Email : " + emailnya,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
        Text(text = "Alamat : " + alamatnya,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
        Text(text = "jenis kelamin : " + jenisnya,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
        Text(text = "status : " + statusnya,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
    }
}




@Composable
fun Greeting() {}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TampilLayout()
}