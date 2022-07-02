import { Box, Button, ButtonGroup, Divider, Stack, TextField, Typography, List, ListItem } from '@mui/material';
import Container from '@mui/material/Container';
import { useState, useEffect} from 'react';
import { useForm } from 'react-hook-form';
import './style.css'


export default function PeixesCadastrados( {handleTabs} ) {
    const {register, handleSubmit} = useForm();
    const [peixe, setPeixe] = useState([{}]);
    const[input, setInput] = useState('');

    useEffect(() => {
        (async () => {
            fetch("http://localhost:8080/fishes")
            .then(response => {
                return response.json();
            })
            .then( jsondata => setPeixe(jsondata));
        })();
    }, []);

    function deletar (peixe1) {
        if(window.confirm("Deseja realmente excluir este peixe?")===true){
            var url = "http://localhost:8080/fishes/"+peixe1;
        fetch(url, {
            method: "DELETE",
            body: JSON.stringify(peixe1),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        })
        .then(response => response.json())
        .then(json => console.log(json))
        .catch(err => console.log(err));
        alert("Peixe excluído com sucesso!");
        window.location.reload(false);
    }}

    function list(peixe) {
        return(
            <div>
            <List>
                <ListItem >
                <h2 textAlign="center">Peixes cadastrados</h2>
                </ListItem>
                <Divider />
            </List>     
        {peixe.map((p)=>(
            <div>
                <Container >
                    <Stack direction='row' spacing={5} margin={4}>                   
                        <Stack flexdirection='column' spacing={2} style ={{width: '100%'}}fontSize={20}  >
                            <Stack direction='row' spacing={1} >
                                <Typography style={{fontWeight: 700}}>Identificador:</Typography>
                                <Typography>{p.id}</Typography>
                            </Stack>
                            <Stack direction='row' spacing={1} >
                                <Typography style={{fontWeight: 700}}>PitTag:</Typography>
                                <Typography>{p.pittag}</Typography>
                            </Stack>
                            <Stack direction='row' spacing={1} >
                                <Typography style={{fontWeight: 700}}>Nome Científico da Espécie: </Typography>
                                <Typography>{p.scientificName}</Typography>
                            </Stack>
                            
                            <Stack direction='row' spacing={1} >
                                <Typography style={{fontWeight: 700}}>Local da Captura:</Typography>
                                <Typography>{p.captureLocation}</Typography>
                            </Stack>
                            <Stack direction='row' spacing={1} >
                                <Typography style={{fontWeight: 700}}>Data da Soltura:</Typography>
                                <Typography>{p.releaseDate}</Typography>
                            </Stack>
                            <Stack direction='row' spacing={1} >
                                <Typography style={{fontWeight: 700}}>Local da Soltura:</Typography>
                                <Typography>{p.releaseLocation}</Typography>
                            </Stack>
                            <Stack direction='row' spacing={1} >
                                <Typography style={{fontWeight: 700}}>Código de Amostra do DNA:</Typography>
                                <Typography>{p.dnaSample}</Typography>
                            </Stack>
                            <Stack direction='row' spacing={1} >
                                <Typography style={{fontWeight: 700}}>Recaptura:</Typography>
                                <Typography>{p.recapture ? "Sim" : "Não"}</Typography>
                            </Stack>
                                                                  
                        </Stack>
                    
                        <Stack direction='column'>                   
                        <Box>
                            <ButtonGroup size="small" aria-label=" small button group" variant='text' color="inherit">
                                <Button size='large' variant="outlined" color="primary" onClick={(e) => handleTabs(e, 1) && setPeixe(p)}>Editar</Button>
                                <Button size='large' variant="outlined" color="error" onClick={() => deletar(p.id)}>Excluir</Button>
                            </ButtonGroup>
                        </Box>
                        </Stack>
                    </Stack>        
                </Container>
                <Divider/>
            </div>
            ))}
        </div>
        )
    }

    //FILTRA DADOS DA LISTA
    function filter(peixe) {
        let expressao = new RegExp(input, 'i')
        let resultado = peixe.filter(
            (peixe) => {
                
                if(expressao.test(peixe.recapture ? "Sim" : "Não")){
                    return peixe }
                if(
                    expressao.test(peixe.pittag) ||
                    expressao.test(peixe.scientificName) ||
                    expressao.test(peixe.captureLocation) ||
                    expressao.test(peixe.releaseDate) ||
                    expressao.test(peixe.releaseLocation) ||
                    expressao.test(peixe.dnaSample)||
                    expressao.test(peixe.recapture)){
                    return peixe }
            })
        return list(resultado)  
    }
  
    return (
        <div>
            <center>
                <TextField
                    value={input}
                    onChange={(e) => {setInput(e.target.value)}}
                    label="Digite para buscar um peixe"
                    variant='outlined'
                    color='primary'
                    required
                    style ={{width: '60%', marginBottom:20, marginTop: 20}}  
                />
            </center>
            {console.log(input)}
            <Divider /> 
            {
                filter(peixe)
            } 
        </div>
    )
}

    /*const onSubmitPittag = async (form) =>  {
        const response = await fetch(`http://localhost:8080/fishes/pittag/${form.pittag}`, {
            method: 'GET'
        });
        setPeixe([await response.json()]);
    }

    const onSubmitScientificName = async (form) =>  {
        const response = await fetch(`http://localhost:8080/fishes?scientificName=${form.scientificName}`, {
            method: 'GET'
        });
        setPeixe(await response.json());
    }

    return (
        <div>
            <Box display='flex'>
                <form onSubmit={handleSubmit(onSubmitPittag)} style={{marginRight: '20px'}}>
                    <TextField
                        label="Digite para buscar por pittag"
                        variant='outlined'
                        color='primary'
                        required
                        {...register("pittag")}
                    />
                </form>

                <form onSubmit={handleSubmit(onSubmitScientificName)}>
                    <TextField
                        label="Digite para buscar por nome científico"
                        variant='outlined'
                        color='primary'
                        required
                        {...register("scientificName")}
                    />
                </form>
            </Box>*/
        /*{peixe.map((fish, index)=>(
        <div>
            <br/>
            <Container key={index} >
                <Stack direction='row'>
                    <Stack direction='column' spacing={2}>
                        {console.log(peixe)}
                        <Typography>Identificador: {fish.id}</Typography>
                        <Typography>PitTag: {fish.pittag}</Typography>
                        <Typography>Nome Científico da Espécie: {fish.scientificName}</Typography>
                        <Typography>Local da Captura: {fish.captureLocation}</Typography>
                        <Typography>Data da Soltura: {fish.releaseDate}</Typography>
                        <Typography>Local da Soltura: {fish.releaseLocation}</Typography>
                        <Typography>Código de Amostra do DNA: {fish.dnaSample}</Typography>
                        <Typography>Recaptura: {fish.recapture ? "Sim" : "Não"}</Typography>
                    </Stack>

                    <Stack direction='row' flex={1} justifyContent='flex-end'>
                        <Box>
                            <ButtonGroup size="small" aria-label=" small button group" variant='text' color="inherit">
                                <Button size='large' variant="outlined" color="primary" onClick={(e) => handleTabs(e, 1) && setPeixe(fish)}>Editar</Button>
                                <Button size='large' variant="outlined" color="error" onClick={() => deletar(fish.id)}>Excluir</Button>
                            </ButtonGroup>
                        </Box>
                    </Stack>
                </Stack>
            </Container>
            <br/>
            <Divider/>
        </div>
        ))
    }</div>
    )
}*/