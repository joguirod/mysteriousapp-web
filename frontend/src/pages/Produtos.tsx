import { useEffect, useState } from 'react';
import axios from 'axios';

interface Produto {
  id?: number;
  name: string;
  description: string;
  price: number;
  quantity: number;
}

const Produtos = () => {
  const [produtos, setProdutos] = useState<Produto[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [showModal, setShowModal] = useState<boolean>(false);
  const [editMode, setEditMode] = useState<boolean>(false);
  const [selectedProduct, setSelectedProduct] = useState<Produto | null>(null);
  const [novoProduto, setNovoProduto] = useState<Produto>({
    name: '',
    description: '',
    price: 0,
    quantity: 0
  });

  useEffect(() => {
    fetchProdutos();
  }, []);

  // BUSCAR
  const fetchProdutos = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/product');
      setProdutos(response.data);
    } catch (err) {
      setError('Erro ao carregar produtos. Tente novamente mais tarde.');
      console.error('Erro ao buscar produtos:', err);
    } finally {
      setLoading(false);
    }
  };

  // CRIAR
  const handleSaveProduto = async () => {
    if (!novoProduto.name || !novoProduto.description || novoProduto.price <= 0 || novoProduto.quantity <= 0) {
      alert("Preencha todos os campos corretamente.");
      return;
    }

    try {
      if (editMode && selectedProduct) {
        await axios.put(`http://localhost:8080/api/product/edit/${selectedProduct.id}`, novoProduto);
      } else {
        await axios.post('http://localhost:8080/api/product', novoProduto);
      }
      
      setShowModal(false);
      fetchProdutos();
      setNovoProduto({ name: '', description: '', price: 0, quantity: 0 });
      setEditMode(false);
    } catch (error) {
      console.error("Erro ao salvar produto:", error);
      alert("Erro ao salvar produto. Tente novamente.");
    }
  };

  // DELETAR
  const handleDeleteProduto = async (id: number) => {
    if (window.confirm("Tem certeza que deseja excluir este produto?")) {
      try {
        await axios.delete(`http://localhost:8080/api/product/delete/${id}`);
        fetchProdutos();
      } catch (error) {
        console.error("Erro ao deletar produto:", error);
        alert("Erro ao deletar produto. Tente novamente.");
      }
    }
  };

  // MOODAL DE EDIÇÃO 
  const handleEditProduto = (produto: Produto) => {
    setNovoProduto(produto);
    setSelectedProduct(produto);
    setEditMode(true);
    setShowModal(true);
  };

  return (
    <div className="w-full h-full flex flex-col">
      <h1 className="text-4xl font-bold mb-6">Produtos</h1>

      <button
        className="btn-primary self-start mb-6"
        onClick={() => {
          setEditMode(false);
          setNovoProduto({ name: '', description: '', price: 0, quantity: 0});
          setShowModal(true);
        }}
      >
        Adicionar Produto
      </button>

      {loading ? (
        <p className="text-lg text-gray-600">Carregando produtos...</p>
      ) : error ? (
        <p className="text-lg text-red-600">{error}</p>
      ) : (
        <div className="grid grid-cols-3 gap-6">
          {produtos.map((produto) => (
            <div key={produto.id} className="bg-white shadow-lg rounded-lg p-6 relative">
              <h2 className="text-xl font-semibold text-gray-800">{produto.name}</h2>
              <p className="text-gray-600">{produto.description}</p>
              <p className="text-lg font-bold text-green-600 mt-2">R$ {produto.price.toFixed(2)}</p>
              <p className="text-sm text-gray-500">Quantidade: {produto.quantity}</p>

              <div className="absolute top-2 right-2 flex space-x-2">
                <button
                  onClick={() => handleEditProduto(produto)}
                  className="btn-secondary text-sm"
                >
                  Editar
                </button>
                <button
                  onClick={() => handleDeleteProduto(produto.id!)}
                  className="btn-danger text-sm"
                >
                  Excluir
                </button>
              </div>
            </div>
          ))}
        </div>
      )}

      {showModal && (
        <div className="fixed inset-0 bg-gray-900 bg-opacity-50 flex justify-center items-center">
          <div className="bg-white p-6 rounded-lg shadow-lg w-1/3">
            <h2 className="text-2xl font-bold mb-4">
              {editMode ? "Editar Produto" : "Adicionar Novo Produto"}
            </h2>
            <input
              type="text"
              placeholder="Nome do Produto"
              className="w-full border rounded-lg p-2 mb-4"
              value={novoProduto.name}
              onChange={(e) => setNovoProduto({ ...novoProduto, name: e.target.value })}
            />
            <input
              type="text"
              placeholder="Descrição"
              className="w-full border rounded-lg p-2 mb-4"
              value={novoProduto.description}
              onChange={(e) => setNovoProduto({ ...novoProduto, description: e.target.value })}
            />
            <input
              type="number"
              placeholder="Preço"
              className="w-full border rounded-lg p-2 mb-4"
              value={novoProduto.price}
              onChange={(e) => setNovoProduto({ ...novoProduto, price: parseFloat(e.target.value) })}
            />
            <input
              type="number"
              placeholder="Quantidade"
              className="w-full border rounded-lg p-2 mb-4"
              value={novoProduto.quantity}
              onChange={(e) => setNovoProduto({ ...novoProduto, quantity: parseInt(e.target.value) })}
            />
            <div className="flex justify-end space-x-4">
              <button
                className="btn-secondary"
                onClick={() => setShowModal(false)}
              >
                Cancelar
              </button>
              <button
                className="btn-primary"
                onClick={handleSaveProduto}
              >
                {editMode ? "Salvar Alterações" : "Adicionar"}
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Produtos;
