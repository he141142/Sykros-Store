import React, { PureComponent } from "react";
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { StyleSheet, Text, View } from "react-native";



export class TopHeader extends React.PureComponent {

    render(): React.ReactNode {
        return(
            <>
                <Navbar bg="primary" variant="dark">
                    <Container>
                    <Navbar.Brand href="#home">Navbar</Navbar.Brand>
                        <Nav className="me-auto">
                            <Nav.Link href="#home">Contact</Nav.Link>
                            <Nav.Link href="#features">Login</Nav.Link>
                            <Nav.Link href="#features">Register</Nav.Link>
                        </Nav>
                    </Container>
                </Navbar>
            </>
        );
    }
}

