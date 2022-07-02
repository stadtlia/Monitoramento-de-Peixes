import { Button, Container, Box, Divider, FormControlLabel, Stack, styled, FormGroup, List, ListItem, ListItemText, Switch, TextField } from '@mui/material';
import React, { useState } from 'react'; 
import Grid from '@mui/material/Grid';
import { useForm } from 'react-hook-form';
import './style.css'

function CadastroPassagens(){
 
  const{register,handleSubmit}=useForm();

  //Upload CSV
  const csvSubmit = (e) => {
    const csvData = new FormData();
    csvData.append("csvFile", e.target.files[0])        

    fetch("http://localhost:8080/passes/upload",
        {
            method: "POST",
            body: csvData
        })
        .then(response => response.json())
        .then(json => console.log(json))
        .catch(err => console.log(err));
        alert("Arquivo inserido com sucesso!");
        window.location.reload(false);
}

const Input = styled('input')({
    display: 'none',
  });
  
  //Post na tela de cadastro
  const onSubmit=(e)=>{
    console.log(e);
    fetch("http://localhost:8080/passes",{
      method: "POST",
      body: JSON.stringify(e),
      headers: {"Content-type": "application/json; charset=UTF-8"}
    })
    .then(response => response.json())
    .then(json => console.log(json))
    .catch(err => console.log(err));
    window.location.reload(false);
  }

 return (
  <Container>
    
  <form onSubmit={handleSubmit(onSubmit)} >
    <div>
      <List>
        <ListItem >
          <h2 textAlign="center">Dados de cadastro das passagens</h2>
        </ListItem>
        <Divider/>
      </List>
      
      <Grid container spacing={2} style={{ justifyContent:'center', textAlign: 'center'}} >
        <Grid item sx={{ '& .MuiTextField-root': { m: 1, width: '25ch' }, }} 
        autoComplete="off" >
          
                
                
          <TextField
            name='antennaId'
            label="Id da Antena"
            variant='outlined'
            color='primary'
            required
            style = {{width: '100%'}}
            {...register("antennaId")}
           />

          <TextField
            name='fishId'
            label="Id do peixe"
            variant='outlined'
            color='primary'
            required
            style = {{width: '100%'}}
            {...register("fishId")}
          />

        </Grid>
  
      </Grid>
    </div>
    
    <br/>
    <div style={{ justifyContent:'center', textAlign: 'right'}} >
      <label htmlFor="csvFile">
        <Input accept=".csv" id="csvFile" multiple type="file" onChange={csvSubmit}/>
        <Button size="large" variant="contained" component="span" sx={{margin: "5px"}}>Upload</Button>
      </label>
      <Button  size='large' variant="contained" color="success" type='submit' style={{margin: '5px'}} >Salvar</Button>
      <Button  size='large' variant="outlined" color="error" style={{margin: '5px'}}>Cancelar</Button>
      
  </div>
</form>
</Container>
 );   
}
export default CadastroPassagens;