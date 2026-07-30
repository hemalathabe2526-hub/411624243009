import {Link} from 'react-router-dom';
export default function NavBar() {
    return (
        <ul>
            {/* <li>
                <Link to='/add'>Add</Link>
            </li> */}

            {/* OR */}
            
            <li><a href="/">Home</a></li>
            <li><a href="/add">Add</a></li>
            <li><a href="/subtract">Subtract</a></li>
            <li><a href="/multiply">Multiply</a></li>
        </ul>
    )
}