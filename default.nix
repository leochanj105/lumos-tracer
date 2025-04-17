{ pkgs ? import <nixpkgs> {}
, lumosTracingFramework
}:

let
  inherit (pkgs)
    lib
    maven
    jdk8
    aspectj;
in
maven.buildMavenPackage {
  pname = "lumos-tracer";
  version = "0.0.1";

  src = ./.;
  mvnHash = "sha256-dqsC3hWEYghr5I2KKxHC+l/Rb3KtTZrZ2FrJHNmbW4A=";

  mvnParameters = lib.escapeShellArgs [
    "clean"
    "install"
    "package"
  ];

  mvnFetchExtraArgs = {
    # Includes packages from the tracing framework.
    preBuild = ''
      mkdir -p $out
      cp -dpR ${lumosTracingFramework.fetchedMavenDeps}/.m2 $out
      chmod +w -R $out/.m2
    '';
  };

  nativeBuildInputs = [
    jdk8
    aspectj
  ];

  mvnJdk = jdk8;

  postInstall = ''
    mv target $out
  '';
}
