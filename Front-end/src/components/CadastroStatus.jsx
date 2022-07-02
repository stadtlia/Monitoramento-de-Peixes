import { Button, Container, Divider, FormControlLabel, FormGroup, List, ListItem, ListItemText, Switch, TextField } from '@mui/material';
import React, { useState } from 'react'; 
import Grid from '@mui/material/Grid';
import { useForm } from 'react-hook-form';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateTimePicker } from '@mui/x-date-pickers/DateTimePicker';
import './style.css'


function CadastroStatus(){
  
  const{register,handleSubmit}=useForm();
  const [clearedDate, setClearedDate] = React.useState(null);
  const[status,setStatus]=useState(false)

  const onSubmit=(e)=>{
    console.log(e);
    fetch("http://localhost:8080/status",{
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
  <Container sx={{width: "100%"}}>
  <form onSubmit={handleSubmit(onSubmit)} >
    <div>
      <List>
        <ListItem >
          <h2 textAlign="center">Dados de cadastro de status</h2>
        </ListItem>
        <Divider/>
      </List>
      <Grid container spacing={2} style={{ justifyContent:'center', textAlign: 'center'}} >
        <Grid item sx={{ '& .MuiTextField-root': { m: 1, width: '25ch' }, }} autoComplete="off" >
          <TextField
            name='antennaId'
            label="Id da Antena"
            variant='outlined'
            color='primary'
            required
            style = {{width: '100%'}}
            {...register("antennaId")}
           />

        <LocalizationProvider dateAdapter={AdapterDateFns}>
          <DateTimePicker
            color='secondary'
            name='statusChangeDate'
            label="Data de mudança de status"
            value={clearedDate}
            inputFormat="yyyy-MM-dd'T'HH:mm:ss"
            onChange={(newValue) => setClearedDate(newValue)}
            renderInput={(params) => (
          <TextField {...params} style ={{width: '100%'}} variant='outlined' required 
            {...register("statusChangeDate")}/>
                )}/>
        </LocalizationProvider>

        <FormGroup>
            <FormControlLabel label="Status" labelPlacement="top" onChange  = { (e) => {setStatus(e.target.checked)}} control={<Switch /> } {...register("status")} />
        </FormGroup>

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
export default CadastroStatus;