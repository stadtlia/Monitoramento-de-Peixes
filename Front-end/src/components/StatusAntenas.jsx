import { Divider, Stack, Typography, List, ListItem, Box } from '@mui/material';
import Container from '@mui/material/Container';
import { useState, useEffect } from 'react';
import './style.css';
import antenasimg from './antenas.jpg';
import { margin } from '@mui/system';

export default function StatusAntenas() {
    const [antenas,setAntenas]=useState([])
    const [status,setStatus]=useState([])

    useEffect(()=>{
    fetch("http://localhost:8080/status")
    .then(response => {
        return response.json();
    })
    .then( jsondata => { setAntenas(jsondata); setStatus(jsondata);  console.log(jsondata); return true;});
    },[]);

    return (
        <div>
            <List>
                <ListItem >
                <h2 textAlign="center">Status das antenas</h2>
                </ListItem>
                <Divider />
            </List>
            <Box  sx={{ bgcolor: 'background.paper', display: 'flex', margin: '50px' }}>
                <img src={antenasimg} width="100%" alt="Mapa das antenas" />
            </Box>
       
        {antenas.map((statusAntenna)=>(

            <Container>
                
                <Divider />

                <Stack direction='row' spacing={50} marginTop='15px'>
                    <Stack direction='column' spacing={3} >
                        <Typography>Número da antena: {statusAntenna.antenna.id}</Typography>
                        <Typography>Status: {statusAntenna.status ? "Ligada" : "Desligada"}</Typography>
                        <Typography>Latitude: {statusAntenna.antenna.latitude}</Typography>
                        <Typography>Longitude: {statusAntenna.antenna.longitude}</Typography>
                        <Typography>Data de instalação: {statusAntenna.antenna.installationDate}</Typography>
                        <Typography>Data da mudança do status: {statusAntenna.statusChangeDate}</Typography>
                        <Typography>Data Desativação: {statusAntenna.antenna.desativationDate ? statusAntenna.antenna.desativationDate : "-"}</Typography>
                    </Stack>
                </Stack>
                <br/>
                <Divider />
             
            </Container>
        ))}
        </div>
    )
}