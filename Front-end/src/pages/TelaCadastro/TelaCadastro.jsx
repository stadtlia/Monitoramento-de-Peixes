import React, { useState } from 'react';
import {Tabs,Tab, Container, Divider} from '@mui/material'
import CadastroPeixes from '../../components/CadastroPeixes';
import CadastroAntenas from '../../components/CadastroAntenas';
import CadastroPassagens from '../../components/CadastroPassagens';
import CadastroStatus from '../../components/CadastroStatus';
import PeixesCadastrados from '../../components/PeixesCadastrados';
import Box from '@mui/material/Box';


import './TelaCadastro.css'
import RegistroPassagens from '../../components/RegistroPassagens';
import StatusAntenas from '../../components/StatusAntenas';
import Antenas from '../../components/Antenas';
import UpdatePeixes from '../../components/UpdatePeixes';


export default function TelaCadastro(){

const [value, setValue] = useState(0)

const handleTabs=(e, val) => {
  setValue(val)
}

function a11yProps(index) {
  return {
    id: `vertical-tab-${index}`,
    'aria-controls': `vertical-tabpanel-${index}`,
  };
}

return(
  <Box  sx={{ bgcolor: 'background.paper', display: 'flex', height: '100vh' }}>

          <Tabs  className='Tabs' value={value} onChange={handleTabs} orientation = "vertical" variant="scrollable" scrollButtons="auto" sx={{borderRight: 1, borderColor: 'divider', width: "15%", minWidth: '200px'}} >
            <Tab label="Cadastro de Peixes"{...a11yProps(0)}/>
            <Tab label="Atualização de Peixes"{...a11yProps(1)}/>
            <Tab label="Cadastro Antenas"{...a11yProps(2)}/>
            <Tab label="Cadastro Passagens"{...a11yProps(3)}/>
            <Tab label="Cadastro Status"{...a11yProps(4)}/>
            <Tab label="Peixes Cadastrados"{...a11yProps(5)}/>
            <Tab label="Antenas Cadastradas"{...a11yProps(6)}/>
            <Tab label="Registro Passagens"{...a11yProps(7)}/>
            <Tab label="Status das Antenas"{...a11yProps(8)}/>
          </Tabs>
          
         <div style={{width:'85%'}}>
          <TabPanel value={value} index={0}><CadastroPeixes/></TabPanel>
          <TabPanel value={value} index={1}><UpdatePeixes/></TabPanel> 
          <TabPanel value={value} index={2}><CadastroAntenas/></TabPanel> 
          <TabPanel value={value} index={3}><CadastroPassagens/></TabPanel> 
          <TabPanel value={value} index={4}><CadastroStatus/></TabPanel> 
          <TabPanel value={value} index={5}><PeixesCadastrados handleTabs={handleTabs}/></TabPanel>
          <TabPanel value={value} index={6}><Antenas/></TabPanel>
          <TabPanel value={value} index={7}><RegistroPassagens/></TabPanel>
          <TabPanel value={value} index={8}><StatusAntenas/></TabPanel>
        </div>
      </Box>
    );
}

function TabPanel(props){
  const {children,value,index}=props;
  return(
    <div>
      {
        value===index &&(
          <div>{children}</div>
        )
      }
    </div>
  )
}

