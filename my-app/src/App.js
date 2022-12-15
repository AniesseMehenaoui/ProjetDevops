import './App.css';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import ResultatListeRecettes from './component/ResultatListeRecettes';
import Resultat from './component/Resultat';
import RecipeComponent from './component/RecipeComponent';
import RecommendedRecipeComponent from './component/RecommendedRecipeComponent';
import AjoutRecettes from './component/AjoutRecettes';
import Historique from './component/Historique';
import React, { useEffect, useState } from 'react'
import {  useNavigate, useLocation } from 'react-router-dom'
import Ingredients from './component/Ingredients';
import Personnel from './component/Personnel';
import Resultat from './component/Resultat';
import Header from './component/Header';
import RecipeComponent from './component/RecipeComponent'
import Login from "./component/Login";
import AddUser from "./component/AddUser";
import Spinner from 'react-bootstrap/Spinner'
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';


export const AUTH_TOKEN_KEY = 'jhi-authenticationToken';
const UserConnected = ({ setUserInfo, userInfo }) => {
  const history = useNavigate();
  let location = useLocation();

  React.useEffect(() => {
    setUserInfo(null)
    axios.get('/isConnected').then(response => {
      setUserInfo(response.data)
    }, () => {
      if (location.pathname !== '/addUser') {
        history("/login")
      }
    })
  }, [history, setUserInfo, location.pathname]);

  return (<>
    {userInfo && <Header userInfo={userInfo} setUserInfo={setUserInfo} />}
  </>)
}

function App() {

  const [userInfo, setUserInfo] = useState('');
  const [loading, setLoading] = useState(false)

  useEffect(() => {
    axios.interceptors.request.use(function (request) {
      const token = sessionStorage.getItem(AUTH_TOKEN_KEY)
      if (token) {
        request.headers.Authorization = `Bearer ${token}`;
      }
      setLoading(true)
      return request
    }, (error) => {
      setLoading(false)
      return Promise.reject(error);
    });

    axios.interceptors.response.use(function (response) {
      setLoading(false)
      return response;
    }, (error) => {
      setLoading(false)
      return Promise.reject(error);
    });
  })
  return (
    <div>

      {loading && (
        <div className="background-spinner">
          <div className="spinner">
            <Spinner animation="grow" variant="light" />
          </div>
        </div>
      )}
      <UserConnected userInfo={userInfo} setUserInfo={setUserInfo} />
      <div className="App">
        <Routes>
        <Route path='Historique' element={<Historique/>}/>
          <Route path='/AjoutRecettes' element={<AjoutRecettes/>}/>
          <Route path='/Resultat' element={<Resultat/>}/>
          <Route path = '/ResultatListeRecettes' element = {<ResultatListeRecettes/>}/>
          <Route path='/RecipeComponent' element={<RecipeComponent/>}/>
          <Route path='/recommended' element={<RecommendedRecipeComponent/>}/>
          <Route path="personnel" element={<Personnel />} />
          <Route path="ingredients" element={<Ingredients />} />
          <Route path="Resultat" element={<Resultat />} />
          <Route path="recipeComponent" element={<RecipeComponent />} />
          <Route path="addUser" element={<AddUser setUserInfo={setUserInfo} />} />
          <Route path="*" element={<Login setUserInfo={setUserInfo} />} />
        </Routes>
      </div>
    </div>
  );
}



export default App;

