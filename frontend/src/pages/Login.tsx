import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Login = () => {
  const navigate = useNavigate();
  const [isLogin, setIsLogin] = useState(true);
  const [formData, setFormData] = useState({ username: "", email: "", password: "" });
  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setErrorMessage("");
    setSuccessMessage("");

    if (!formData.email || !formData.password || (!isLogin && !formData.username)) {
      setErrorMessage("Preencha todos os campos.");
      return;
    }

    try {
      const url = isLogin
        ? "http://localhost:8080/api/mysteriousUser/auth/signin"
        : "http://localhost:8080/api/mysteriousUser/auth/signup";

      const response = await axios.post(url, formData, { headers: { "Content-Type": "application/json" } });

      if (isLogin) {
        const { token, mysteriousUser } = response.data;
        localStorage.setItem("userToken", token);
        localStorage.setItem("mysteriousUserId", mysteriousUser.mysteriousUserId);
        console.log(localStorage.getItem("userToken"));
        console.log(localStorage.getItem("mysteriousUserId"));
        navigate("/dashboard");
      } else {
        setSuccessMessage("Cadastro realizado com sucesso! Faça login para continuar.");
        setTimeout(() => setIsLogin(true), 2000);
      }
    } catch (error) {
      setErrorMessage(isLogin ? "Erro ao autenticar. Verifique suas credenciais." : "Erro ao cadastrar. Verifique os dados.");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-[80vh] w-[80vw] bg-gray-900">
      <div className="bg-white rounded-lg p-10 shadow-xl w-full max-w-md">
        <h1 className="text-3xl font-semibold text-[#023047] text-center">{isLogin ? "Login" : "Cadastro"}</h1>
        <p className="text-gray-500 text-sm text-center mb-4">
          {isLogin ? "Digite seus dados para acessar o painel de gestão." : "Preencha os dados para criar sua conta."}
        </p>

        {errorMessage && <p className="text-red-500 text-sm text-center bg-red-100 p-2 rounded-md">{errorMessage}</p>}
        {successMessage && <p className="text-green-500 text-sm text-center bg-green-100 p-2 rounded-md">{successMessage}</p>}

        <form onSubmit={handleSubmit} className="flex flex-col gap-3">
          {!isLogin && (
            <>
              <label className="font-semibold text-sm text-[#023047]">Nome de usuário</label>
              <input
                type="text"
                placeholder="Digite seu nome"
                className="border p-3 rounded-md focus:border-[#111827] outline-none transition-all"
                value={formData.username}
                onChange={(e) => setFormData({ ...formData, username: e.target.value })}
              />
            </>
          )}

          <label className="font-semibold text-sm text-[#023047]">E-mail</label>
          <input
            type="email"
            placeholder="Digite seu e-mail"
            className="border p-3 rounded-md focus:border-[#111827] outline-none transition-all"
            value={formData.email}
            onChange={(e) => setFormData({ ...formData, email: e.target.value })}
          />

          <label className="font-semibold text-sm text-[#023047]">Senha</label>
          <input
            type="password"
            placeholder="Digite sua senha"
            className="border p-3 rounded-md focus:border-[#111827] outline-none transition-all"
            value={formData.password}
            onChange={(e) => setFormData({ ...formData, password: e.target.value })}
          />

          <button
            type="submit"
            className="bg-[#111827] text-white font-semibold p-3 rounded-md transition-all hover:scale-105 hover:bg-[#111827]"
          >
            {isLogin ? "Entrar" : "Cadastrar"}
          </button>

          <p className="text-sm text-gray-500 text-center mt-2">
            {isLogin ? "Não tem uma conta?" : "Já tem uma conta?"}{" "}
            <span
              className="text-[#111827] cursor-pointer hover:underline"
              onClick={() => setIsLogin(!isLogin)}
            >
              {isLogin ? "Cadastre-se" : "Faça login"}
            </span>
          </p>
        </form>
      </div>
    </div>
  );
};

export default Login;
