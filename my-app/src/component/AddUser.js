import React from 'react'
import { Link } from "react-router-dom";
import axios from 'axios';
import './AddUser.css'
import SimpleModal from './SimpleModal';
import { AUTH_TOKEN_KEY } from '../App';
import { useNavigate } from 'react-router-dom';

export default class AddUser extends React.Component {
    constructor() {
        super();
        this.state = { userData: {}, showModal: false }
      }
    
      handleChange = (event) => {
        let currentState = { ...this.state.userData };
        currentState[event.target.name] = event.target.value;
        this.setState({ userData: currentState })
      }
    
      handleCloseModal = () => {
        this.setState({ showModal: false })
      }
    
      onSubmit = (event) =>  {
        event.preventDefault();
        axios.post('/users', {
          ...this.state.userData
        }).then(response => {
           const bearerToken = response?.headers?.authorization;
           if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
             const jwt = bearerToken.slice(7, bearerToken.length);
             sessionStorage.setItem(AUTH_TOKEN_KEY,jwt)
           }
           this.props.setUserInfo(response.data.firstName + " " + response.data.lastName)
           this.props.history("/myBooks")
         }).catch(() => {
           this.setState({ showModal: true })
         })
       }
    
  /*this.setState({ userData: currentState })
  }

  onSubmit = (event) => {
  onSubmit = (event) =>  {
    event.preventDefault();
    console.log("onSubmit")
    console.log(this.state.userData)
  }
    axios.post('/users', {
      ...this.state.userData
    }).then(response => {
       const bearerToken = response?.headers?.authorization;
       if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
         const jwt = bearerToken.slice(7, bearerToken.length);
         sessionStorage.setItem(AUTH_TOKEN_KEY,jwt)
       }
    })
}
*/
  render() {
    return (
      <div /*className="add-user-container"*/>

        <div>
          <h1>M'inscrire</h1>
          <h5>Veuillez remplir les champs ci-dessous pour vous créer un compte :</h5>
          <div>
            <form className="add-user-container" onSubmit={this.onSubmit}>
              <div>
                <label>E-mail:</label>
                <input name="email" type="text" className="form-control" onChange={this.handleChange} />
              </div>
              <div>
                <label>Nom:</label>
                <input name="lastName" type="text" className="form-control" onChange={this.handleChange} />
              </div>
              <div>
                <label>Prénom:</label>
                <input name="firstName" type="text" className="form-control" onChange={this.handleChange} />
              </div>
              
              <div>
                <label>Mot de Passe:</label>
                <input name="password" type="password" className="form-control" onChange={this.handleChange} />
              </div>
              <div className="container-valid text-center">
                <input type="submit" value="S'inscrire" className="btn btn-primary" onChange={this.handleChange} />
              </div>
            </form>
          </div>
          <div><Link to="/">Retour à l'accueil</Link></div>
        </div>
        <SimpleModal
         
        ></SimpleModal>
      </div>
      
    )
  }
}


/*<div>
                <label>Age:</label>
                <input name="age" type="number" className="form-control" onChange={this.handleChange} />
              </div>
              <div>
                <label>Sexe:</label>
                <input name="sexe" type="text" className="form-control" onChange={this.handleChange} />
              </div>
              <div>
                <label>Poids:</label>
                <input name="poids" type="number" className="form-control" onChange={this.handleChange} />
              </div>
              <div>
                <label>Taille:</label>
                <input name="taille" type="number" className="form-control" onChange={this.handleChange} />
              </div>
              */
//remettre dans modal et parametrer
/* title={"Mail déja utilisé"}
          bodyTxt={"Cet email est déja utilisé, merci d'en saisir un autre"}
          handleCloseModal={this.handleCloseModal}
          showModal={this.state.showModal}*/
// Wrap and export
export function Wrapper(props) {
    const history = useNavigate();
    return <AddUser {...props} history={history} />;
  }