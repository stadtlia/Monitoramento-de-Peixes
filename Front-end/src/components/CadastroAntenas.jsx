import { Button, Container, Divider, FormControlLabel, FormGroup, List, ListItem, ListItemText, Switch, TextField } from '@mui/material';
import React, { useState } from 'react'; 
import Grid from '@mui/material/Grid';
import { useForm } from 'react-hook-form';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateTimePicker } from '@mui/x-date-pickers/DateTimePicker';
import './style.css'


function CadastroAntenas(){
  const [clearedDate, setClearedDate] = React.useState(null);
  const [clearedDate1, setClearedDate1] = React.useState(null);
 
  const{register,handleSubmit}=useForm();

  const onSubmit=(e)=>{
    console.log(e);
    fetch("http://localhost:8080/antennas",{
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
          <h2 textAlign="center">Dados de cadastro da antena</h2>
        </ListItem>
        <Divider />
      </List>
      <Grid container spacing={2} style={{ justifyContent:'center', textAlign: 'center'}} >
        <Grid item xs={5} sx={{ '& .MuiTextField-root': { m: 1, width: '100%' }, }} autoComplete="off" >
          

          <TextField
            name='latitude'
            label="Latitude"
            variant='outlined'
            color='primary'
            required
            style = {{width: '100%'}}
            {...register("latitude")}
          />

          <TextField
            name='longitude'
            label="Longitude"
            variant='outlined'
            color='primary'
            required
            style = {{width: '100%'}}
            {...register("longitude")}
          />


        </Grid>
        
        <Grid item xs={5}
            sx={{
              '& .MuiTextField-root': { m: 1, width: '25ch' },
            }}
            autoComplete="off"
          >
          <LocalizationProvider dateAdapter={AdapterDateFns}>
          <DateTimePicker
            color='secondary'
            name='installationDate'
            label="Data de instalação"
            value={clearedDate}
            inputFormat="yyyy-MM-dd'T'HH:mm:ss"
            onChange={(newValue) => setClearedDate(newValue)}
            renderInput={(params) => (
          <TextField {...params}  style = {{width: '100%'}} variant='outlined' required 
            {...register("installationDate")}/>
                )}/>
        </LocalizationProvider>

        <LocalizationProvider dateAdapter={AdapterDateFns}>
          <DateTimePicker
            color='secondary'
            name='desativationDate'
            label="Data de desativação"
            value={clearedDate1}
            inputFormat="yyyy-MM-dd'T'HH:mm:ss"
            onChange={(newValue1) => setClearedDate1(newValue1)}
            renderInput={(params) => (
          <TextField {...params}  style = {{width: '100%'}} variant='outlined'   
            {...register("desativationDate")}/>
                )}/>
        </LocalizationProvider>
                 
        </Grid>
      </Grid>
    </div>
    
    <br/>
    <div style={{ justifyContent:'center', textAlign: 'right'}} >
      <Button  size='large' variant="contained" color="success" type='submit' style={{margin: '5px',}} >Salvar</Button>
      <Button  size='large' variant="outlined" color="error" >Cancelar</Button>
  </div>
</form>
</Container>
 );   
}
export default CadastroAntenas;