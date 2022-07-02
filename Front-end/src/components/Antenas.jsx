import { Divider, Stack, Typography, ListItem, List } from '@mui/material';
import Container from '@mui/material/Container';
import Avatar from '@mui/material/Avatar';
import { useState, useEffect } from 'react';
import './style.css'

export default function Antenas() {
    const [antenas,setAntenas]=useState([])

    useEffect(()=>{
    fetch("http://localhost:8080/antennas")
    .then(response => {
        return response.json();
    })
    .then( jsondata => { setAntenas(jsondata); console.log(jsondata); return true;});
    },[]);
    
    return (
        <div>
            <List>
                <ListItem >
                <h2 textAlign="center">Antenas cadastradas</h2>
                </ListItem>
                <Divider />
            </List>     
        {antenas.map((antenna)=>(
            
            <Container>     
                <Stack direction='row' spacing={10} marginTop='20px' marginBottom='20px'>
                    <Stack direction='column' spacing={3} >
                        <Avatar sx={{ width: 300, height: 300 }} alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
                    </Stack>
                    <Stack direction='column' spacing={5} >
                        <Typography>Número da antena: {antenna.id}</Typography>
                        <Typography>Latitude: {antenna.latitude}</Typography>
                        <Typography>Longitude: {antenna.longitude}</Typography>
                        <Typography>Data de Instalação: {antenna.installationDate}</Typography>
                        <Typography>Data Desativação: {antenna.desativationDate ? antenna.desativationDate : "-"}</Typography>
                    </Stack>
                </Stack>
                <Divider/>
                <br/>
                            
            </Container>
        ))}
    </div>)
}