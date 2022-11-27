import React, { Component } from 'react';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import ListGroup from 'react-bootstrap/ListGroup';
import SearchService from '../services/SearchService';
import { useState } from 'react';
class RecipeComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            recipe: [],
            s: []
        }
    }



    render() {
        return (
            <div className="row">
                <div><form className="search" >
                    <input type="text" placeholder="search" name="search" onChange={async (event) => {
                        const { search } = SearchService();
                        const value = event.target.value;

                        try {
                            const data = await search(value);
                            this.setState({ s: data.data });
                        }
                        catch (error) {
                            console.log(error);
                        }
                    }} />
                </form>
                </div>
                {
                    this.state.s.map(
                        s =>
                            <Card className="d-flex justify-content-center" border="info" style={{ width: '18rem' }}>
                                <Card.Img variant="top" src={s.image} />
                                <Card.Body>
                                    <Card.Title>{s.label}</Card.Title>
                                    <Card.Text>

                                    </Card.Text>
                                </Card.Body>

                                <ListGroup className="list-group-flush">
                                    <ListGroup.Item><b>PrÃ©partion</b></ListGroup.Item>
                                    {s.ingredientLines.map(line =>
                                        <ListGroup.Item><li>{line}</li></ListGroup.Item>
                                    )}

                                    <ListGroup.Item><b>DurÃ©e</b></ListGroup.Item>
                                    <ListGroup.Item>{s.totalTime}minutes</ListGroup.Item>

                                    <ListGroup.Item><b>Calories</b></ListGroup.Item>
                                    <ListGroup.Item>{String(s.calories).slice(0, 4)}Kcal</ListGroup.Item>

                                    <ListGroup.Item><b>Sugar</b></ListGroup.Item>
                                    <ListGroup.Item>{String(s.totalNutrients.SUGAR.quantity).slice(0, 2)}g</ListGroup.Item>
                                </ListGroup>

                                <Card.Body>

                                </Card.Body>
                            </Card>
                    )
                }
            </div >
        );
    }
}


export default RecipeComponent;