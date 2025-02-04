// import { useState, useEffect } from "react";
// import axios from "axios";

// interface Book {
//   bookId: number;
//   title: string;
//   author: string;
//   publisher: string;
//   genre: string;
//   bookPictureUrl?: string; 
// }

// const Livros = () => {
//   const [books, setBooks] = useState<Book[]>([]); // Definindo o estado corretamente

//   useEffect(() => {
//     const fetchBooks = async () => {
//       try {
//         const response = await axios.get<Book[]>("http://localhost:8080/book");
//         setBooks(response.data);
//       } catch (error) {
//         console.error("Erro ao buscar livros:", error);
//       }
//     };

//     fetchBooks();
//   }, []);

//   return (
//     <div className="w-full h-full flex flex-col">
//       <h1 className="text-4xl font-bold mb-6">Livros</h1>

//       <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6 w-full max-w-7xl">
//         {books.length > 0 ? (
//           books.map((book) => (
//             <div key={book.bookId} className="bg-white shadow-lg rounded-lg p-6 flex flex-col items-center">
//               <img
//                 src={book.bookPictureUrl || "https://via.placeholder.com/150"}
//                 alt={book.title}
//                 className="w-32 h-40 object-cover mb-4 rounded-md"
//               />
//               <h2 className="text-xl font-semibold">{book.title}</h2>
//               <p className="text-gray-600 text-sm">Autor: {book.author}</p>
//               <p className="text-gray-600 text-sm">Gênero: {book.genre}</p>
//               <p className="text-gray-600 text-sm">Editora: {book.publisher}</p>
//             </div>
//           ))
//         ) : (
//           <p className="text-gray-600">Nenhum livro disponível.</p>
//         )}
//       </div>
//     </div>
//   );
// };

// export default Livros;
