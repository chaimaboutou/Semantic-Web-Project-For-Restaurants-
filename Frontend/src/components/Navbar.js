import React from 'react';
import './Navbar.css'; // Importer le fichier CSS
import { FaHome, FaSearch, FaPlus, FaUtensils } from 'react-icons/fa'; // Ajouter l'icône de fourchette et cuillère

const Navbar = () => {
    return (
        <div className="navbar">
            <div className="navbar-left">
                <h1><FaUtensils /> Restaurant List</h1> {/* Icône de fourchette et cuillère à gauche du titre */}
            </div>
            <div className="navbar-right">
                <i><FaHome /></i> {/* Icône pour Home */}
                <i><FaSearch /></i> {/* Icône pour Search */}
                <i><FaPlus /></i> {/* Icône pour Add */}
            </div>
        </div>
    );
};

export default Navbar;
