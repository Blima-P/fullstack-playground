import React, { useEffect, useState } from 'react';

const Profile = () => {
    const [resume, setResume] = useState(null);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const fetchResume = async () => {
            try {
                // A URL deve apontar para o seu back-end Spring Boot
                const response = await fetch('http://localhost:8080/api/resume');
                const data = await response.json();
                setResume(data);
            } catch (error) {
                console.error("Falha ao buscar dados do currículo:", error);
            } finally {
                setIsLoading(false);
            }
        };
        fetchResume();
    }, []);

    if (isLoading) {
        return <div className="text-center text-gray-400">Carregando...</div>;
    }

    if (!resume) {
        return <div className="text-center text-red-500">Erro ao carregar os dados.</div>;
    }

    return (
        <section className="p-8 bg-white rounded-lg shadow-lg max-w-4xl mx-auto mt-10">
            <div className="text-center mb-8">
                <h1 className="text-4xl font-bold text-gray-800 tracking-tight">{resume.name}</h1>
                <p className="text-lg text-gray-500">{resume.summary}</p>
            </div>
            <div className="flex justify-center space-x-6">
                <a href={`mailto:${resume.email}`} className="text-blue-600 hover:text-blue-800 transition duration-300">Email</a>
                <a href={resume.github} target="_blank" rel="noopener noreferrer" className="text-gray-800 hover:text-black transition duration-300">GitHub</a>
                <a href={resume.linkedin} target="_blank" rel="noopener noreferrer" className="text-blue-700 hover:text-blue-900 transition duration-300">LinkedIn</a>
            </div>
        </section>
    );
};

export default Profile;