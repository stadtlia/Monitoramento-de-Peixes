import { Box, Button, ButtonGroup, Divider, Stack, Typography, List, ListItem } from '@mui/material';
import Container from '@mui/material/Container';
import { useState, useEffect } from 'react';
import './style.css'

    export default function RegistroPassagens() {
    const [passagens,setPassagens]=useState([])

    useEffect(()=>{  
    fetch("http://localhost:8080/passes")
    .then(response => {
        return response.json();
    })
    .then( jsondata => setPassagens(jsondata));
    },[]);

    

    function deletar  (pass1) {
        if(window.confirm("Deseja realmente excluir esta passagem?")===true){
            var url = "http://localhost:8080/passes/"+pass1;
        fetch(url, {
            method: "DELETE",
            body: JSON.stringify(pass1),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        })
        .then(response => response.json())
        .then(json => console.log(json))
        .catch(err => console.log(err));
        alert("Passagem excluída com sucesso!");
        window.location.reload(false);
    }}

    return (
        <div>
            <List>
                <ListItem >
                <h2 textAlign="center">Registro de passagens</h2>
                </ListItem>
                <Divider />
            </List>     
        {passagens.map((pass)=>(
            
        <div >
            <Container >
                <br/>
                
                <Stack direction='row' spacing={50} >
                    <Stack direction='column' spacing={3} >
                        {console.log(passagens)}
                        <Typography>Id da passagem: {pass.id}</Typography>
                        <Typography>Identificador do peixe: {pass.fish.id}</Typography>
                        <Typography>PitTag: {pass.fish.pittag}</Typography>
                        <Typography>Nome Científico da Espécie: {pass.fish.scientificName}</Typography>
                        

                    </Stack>

                    <Stack direction='column' spacing={3} >
                        <Typography>Comprimento Total: {pass.fish.totalLength}</Typography>
                        <Typography>Número da Antena: {pass.antenna.id}</Typography>
                        <Typography>Data e Hora do Registro: {pass.fish.registryDate}</Typography>
                    
                        <Box>
                            <ButtonGroup size="small" aria-label=" small button group" variant='text' color="inherit">
                                <Button size='large' variant="outlined" color="error" onClick={() => deletar(pass.id)}>Excluir</Button>
                            </ButtonGroup>
                        </Box>
                    </Stack>

                </Stack>
               <br/>
               <Divider/>
            </Container>
        </div>
        ))}
    </div>
    )
}