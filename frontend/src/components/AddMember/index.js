import React, {useContext, useEffect, useState} from 'react';
import {AddMemberDiv} from "./AddMemberElements";
import '../../styles/global.css'
import '../../styles/addMember.css'
import '../../styles/createFamily.css'
import {Form, Button, FormControl} from 'react-bootstrap';
import AppContext from "../../context/AppContext";
import {addNewMember, changeView} from "../../context/Actions";

function AddMember() {

    const {state, dispatch} = useContext(AppContext);
    const {newMember, loggedUser} = state;
    const { loading, error, addMemberData} = newMember;
    const { id } = loggedUser;
    const [emailID, setEmailID] = useState('');
    const [userName, setUserName] = useState('');
    const [birthDate, setBirthdate] = useState('');
    const [vatNumber, setVatNumber] = useState('');
    const [phone, setPhoneNumber] = useState('');
    const [street, setStreet] = useState('');
    const [city, setCity] = useState('');
    const [houseNumber, setHouseNumber] = useState('');
    const [zipCode, setZipCode] = useState('');

    /*
    useEffect( ()=> {
        addNewMember(dispatch, newMember);
    }, [])

     */

    function handleClick(){
        const newMember = {
            adminID: id,
            emailID: emailID,
            name: userName,
            birthDate: birthDate,
            vatNumber: vatNumber,
            phone: phone,
            street: street,
            city: city,
            houseNumber: houseNumber,
            zipCode: zipCode,
        }
        addNewMember(dispatch, newMember);
        dispatch(changeView('family'));

    }

    return (
        <AddMemberDiv>
            <h3 align="center">Add Family Member</h3>
            <Form className="center-form">
                <Form.Group controlId="emailID">
                    <Form.Label>Email address</Form.Label>
                    <br></br>
                    <Form.Control className="addMember-input" type="email" placeholder="Enter email" onChange={emailID => setEmailID(emailID.target.value)}/>
                </Form.Group>

                <Form.Group controlId="name">
                    <Form.Label>Name</Form.Label>
                    <br></br>
                    <Form.Control className="addMember-input" type="text" placeholder="Name" onChange={userName => setUserName(userName.target.value)}/>
                </Form.Group>

                <Form.Group controlId="birthDate">
                    <Form.Label>BirthDate</Form.Label>
                    <br></br>
                    <Form.Control className="addMember-input" type="text" placeholder="BirthDate" onChange={birthDate => setBirthdate(birthDate.target.value)}/>
                </Form.Group>

                <Form.Group controlId="vatNumber">
                    <Form.Label>VatNumber</Form.Label>
                    <br></br>
                    <Form.Control className="addMember-input" type="text" placeholder="VatNumber" onChange={vatNumber => setVatNumber(vatNumber.target.value)}/>
                </Form.Group>

                <Form.Group controlId="phone">
                    <Form.Label>Phone</Form.Label>
                    <br></br>
                    <Form.Control className="addMember-input" type="text" placeholder="Phone" onChange={phone => setPhoneNumber(phone.target.value)}/>
                </Form.Group>

                <Form.Group controlId="street">
                    <Form.Label>Street</Form.Label>
                    <br></br>
                    <Form.Control className="addMember-input" type="text" placeholder="Street" onChange={street => setStreet(street.target.value)}/>
                </Form.Group>

                <Form.Group controlId="city">
                    <Form.Label>City</Form.Label>
                    <br></br>
                    <Form.Control className="addMember-input" type="text" placeholder="City" onChange={city => setCity(city.target.value)}/>
                </Form.Group>

                <Form.Group controlId="houseNumber">
                    <Form.Label>HouseNumber</Form.Label>
                    <br></br>
                    <Form.Control className="addMember-input" type="text" placeholder="HouseNumber" onChange={houseNumber => setHouseNumber(houseNumber.target.value)}/>
                </Form.Group>

                <Form.Group controlId="zipCode">
                    <Form.Label>ZipCode</Form.Label>
                    <br></br>
                    <Form.Control className="addMember-input" type="text" placeholder="ZipCode" onChange={zipCode => setZipCode(zipCode.target.value)}/>
                </Form.Group>

                <div className="button-position">
                    <Button className="button-two-small" variant="primary" type="submit" onClick={handleClick}>Submit</Button>
                </div>
            </Form>
        </AddMemberDiv>
    )


}




export default AddMember;