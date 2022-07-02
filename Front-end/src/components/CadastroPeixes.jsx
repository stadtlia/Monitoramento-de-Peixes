import { Button, Container, Divider, FormControlLabel, FormGroup, List, ListItem, ListItemText, Switch, TextField } from '@mui/material';
import React, { useState } from 'react'; 
import Grid from '@mui/material/Grid';
import { useForm } from 'react-hook-form';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateTimePicker } from '@mui/x-date-pickers/DateTimePicker';
import './style.css'

function CadastroPeixes(){

  const[recaptura,setRecaptura]=useState(false)  
  const{register,handleSubmit}=useForm();

  const [clearedDate, setClearedDate] = React.useState(null);

  const onSubmit=(e)=>{
    console.log(e);
    fetch("http://localhost:8080/fishes",{
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
          <h2 textAlign="center">Dados de cadastro do peixe</h2>
        </ListItem>
        <Divider/>
      </List>
      
      <Grid container spacing={2} style={{ justifyContent:'center', textAlign: 'center'}} >
        <Grid item xs={5} sx={{ '& .MuiTextField-root': { m: 1, width: '25ch' }, }} autoComplete="off" >
          <TextField
            name='pittag'
            label="PitTag"
            variant='outlined'
            color='primary'
            required
            style = {{width: '100%'}}
            {...register("pittag")}
           />

          <TextField
            name='standardLength'
            label="Comprimento Padrão (cm)"
            variant='outlined'
            color='primary'
            style = {{width: '100%'}}
            {...register("standardLength")}
          />

          <TextField
            name='captureLocation'
            label="Local de Captura"
            variant='outlined'
            color='primary'
            required
            style = {{width: '100%'}}
            {...register("captureLocation")}
          />

          <TextField
            name="releaseLocation"
            label="Local da soltura"
            variant='outlined'
            color='primary'
            required
            style = {{width: '100%'}}
            {...register("releaseLocation")}
          />

          <TextField
            name='dnaSample'
            label="Código da Amostra de DNA"
            variant='outlined'
            color='primary'
            required 
            style = {{width: '100%'}}
            {...register("dnaSample")}
            />
        </Grid>
        
        <Grid item xs={5}
            sx={{
              '& .MuiTextField-root': { m: 1, width: '25ch' },
            }}
            autoComplete="off">
          <TextField
            name='scientificName'
            label="Nome Científico da Espécie"
            variant='outlined'
            color='primary'
            required
            style = {{width: '100%'}}
            {...register("scientificName")}
          />

          <TextField
            name='totalLength'
            label="Comprimento total (cm)"
            variant='outlined'
            color='primary'
            required
            style = {{width: '100%'}}
            {...register("totalLength")}
          />

          <TextField
            name='releaseWeight'
            label="Peso na Soltura (g)"
            variant='outlined'
            color='primary'
            required
            style = {{width: '100%'}}
            {...register("releaseWeight")}
          />

         

<LocalizationProvider dateAdapter={AdapterDateFns}>
          <DateTimePicker
            color='secondary'
            name='releaseDate'
            label="Data de Soltura"
            value={clearedDate}
            style = {{width: '100%'}}
            inputFormat="yyyy-MM-dd'T'HH:mm:ss"
            onChange={(newValue) => setClearedDate(newValue)}
            renderInput={(params) => (
          <TextField {...params} style = {{width: '100%'}} variant='outlined' required 
            {...register("releaseDate")}/>
                )}/>
        </LocalizationProvider>

          <FormGroup>
            <FormControlLabel label="Recaptura" labelPlacement="top" onChange  = { (e) => {setRecaptura(e.target.checked)}} control={<Switch /> } {...register("recapture")} />
          </FormGroup>

          {console.log(recaptura)}

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
export default CadastroPeixes;