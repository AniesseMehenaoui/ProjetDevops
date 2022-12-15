import React from 'react'
import logo from './logokenko.jpg';
import './Login.css'
import axios from 'axios';
import { Link, useNavigate} from "react-router-dom";
import { AUTH_TOKEN_KEY } from '../App';
import SimpleModal from './SimpleModal';


export default class Login extends React.Component {

    constructor() {
        super();
        this.state = { userData: {}, showModal: false }
        this.handleChange = this.handleChange.bind(this)
        this.onSubmit = this.onSubmit.bind(this)
        this.handleCloseModal = this.handleCloseModal.bind(this)
    }

    handleChange(event) {
        let currentState = {...this.state.userData};
        currentState[event.target.name] = event.target.value;
        this.setState({ userData: currentState })
    }

    onSubmit (event) {
        event.preventDefault();
        axios.post('/authenticate', {
            email: this.state.userData.email,
            password: this.state.userData.password
        }).then((response) => {
            const bearerToken = response?.headers?.authorization;
            if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
              const jwt = bearerToken.slice(7, bearerToken.length);
              sessionStorage.setItem(AUTH_TOKEN_KEY,jwt)
            }
            this.props.setUserInfo(response.data.userName)
            this.props.history('/listbooks')
        }).catch(() => {
           this.setState({ showModal: true })
        })
    }

    handleCloseModal() {
        this.setState({ showModal: false })
    }
    
    render() {
    const title = "Login incorrect"
    const bodyTxt = "Login ou mot de passe incorrect"
        return (
            <>
            <div className="login-container">
                <div>
                    <div>
                        <img src={logo} alt="Logo" />
                    </div>
                    <div className="title">
                        Bienvenue sur Kenko!
                    </div>
                    <div className="form-container">
                        <form onSubmit={this.onSubmit}>
                            <span>E-mail: </span>
                            <input type="text" className="form-control" name="email" onChange={this.handleChange}></input>
                            <span>Mot de passe: </span>
                            <input type="password" className="form-control" name="password" onChange={this.handleChange}></input>
                            <div>
                                <input type="submit" className="btn btn-primary" value="Se connecter" />
                            </div>
                        </form>
                    </div>
                    <div><Link to="/addUser">M'inscrire</Link></div>
                </div>
                
            </div>
            
            <SimpleModal  /> </>
            
        )
    }}
    // penser a reparametrer simple modal 
    // Wrap and export
    export function Wrapper(props) {
        const history = useNavigate();
        return <Login {...props} history={history} />;
    }
    